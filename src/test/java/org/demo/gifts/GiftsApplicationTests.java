package org.demo.gifts;

import org.demo.gifts.controllers.GiftCardsController;
import org.demo.gifts.dao.GiftCardDAO;
import org.demo.gifts.data.GiftCard;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;



@SpringBootTest(classes = GiftsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class GiftsApplicationTests {

	private static final String GIFT_CARDS_ENDPOINT = "/giftcards";

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	GiftCardDAO giftCardDAO;

	@Autowired
	private GiftCardsController controller;

	@Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

	@Test
	void getEmptyList() throws Exception {
		mockMvc.perform(get("/giftcards")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string("[]"));
	}

	@Test
	public void testCreateGiftCard() {
		GiftCard giftCard = new GiftCard();
		giftCard.setAmount(11.0);
		giftCard.setCurrency("EUR");
		giftCard.setExpiration(LocalDateTime.of(2021, 1, 1, 0,0,0));
		ResponseEntity<GiftCard> postResponse = restTemplate.postForEntity(getRootUrl() + GIFT_CARDS_ENDPOINT, giftCard, GiftCard.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testCreateGiftCardAndGetList() {
		LocalDateTime before = LocalDateTime.of(2021, 1, 1, 0,0,0);
		LocalDateTime after = LocalDateTime.of(2022, 1, 1, 0,0,0);
		List<GiftCard> values = generateGiftCardList(10, 10.0, before, after);
		List<GiftCard> result = new ArrayList<>();
		for (GiftCard card : values) {
			ResponseEntity<GiftCard> postResponse = restTemplate.postForEntity(getRootUrl() + GIFT_CARDS_ENDPOINT, card, GiftCard.class);
			if (postResponse != null)
				result.add(postResponse.getBody());
		}
		ResponseEntity<List> getResponse = restTemplate.getForEntity(getRootUrl() + GIFT_CARDS_ENDPOINT, List.class);
		assertNotNull(getResponse);
		assertNotNull(getResponse.getBody());
		assertEquals(getResponse.getBody().size(), result.size());
		for(int i = 0; i < getResponse.getBody().size(); i++) {
			GiftCard parsed = convertMapToGiftCard((Map<String, Object>) getResponse.getBody().get(i));
			assertEquals(parsed, result.get(i));
		}
	}

	@Test
	public void testCreateGiftCardAndGetListOfNotExpired() {
		LocalDateTime before = LocalDateTime.of(2021, 1, 1, 0,0,0);
		LocalDateTime after = LocalDateTime.of(2022, 1, 1, 0,0,0);
		List<GiftCard> values = generateGiftCardList(10, 10.0, before, after);
		List<GiftCard> result = new ArrayList<>();
		for (GiftCard card : values) {
			ResponseEntity<GiftCard> postResponse = restTemplate.postForEntity(getRootUrl() + GIFT_CARDS_ENDPOINT, card, GiftCard.class);
			if (postResponse != null)
				result.add(postResponse.getBody());
		}
		ResponseEntity<List> getResponse = restTemplate.getForEntity(getRootUrl() + GIFT_CARDS_ENDPOINT + "?amount={amount}", List.class, 5.0);
		assertNotNull(getResponse);
		assertNotNull(getResponse.getBody());
		assertEquals(getResponse.getBody().size(), result.size() / 2);
	}

	@Test
	public void testCreateGiftCardAndGetById() {
		LocalDateTime before = LocalDateTime.of(2021, 1, 1, 0,0,0);
		LocalDateTime after = LocalDateTime.of(2022, 1, 1, 0,0,0);
		List<GiftCard> values = generateGiftCardList(10, 10.0, before, after);
		List<GiftCard> result = new ArrayList<>();
		for (GiftCard card : values) {
			ResponseEntity<GiftCard> postResponse = restTemplate.postForEntity(getRootUrl() + GIFT_CARDS_ENDPOINT, card, GiftCard.class);
			if (postResponse != null)
				result.add(postResponse.getBody());
		}
		ResponseEntity<GiftCard> getResponse = restTemplate.getForEntity(getRootUrl() + GIFT_CARDS_ENDPOINT + "/1", GiftCard.class);
		assertNotNull(getResponse);
		assertNotNull(getResponse.getBody());
		assertEquals(getResponse.getBody(), result.get(0));
	}

	private GiftCard convertMapToGiftCard(Map<String, Object> map) {
		GiftCard result = new GiftCard();
		result.setId((Integer) map.get("id"));
		result.setAmount((Double) map.get("amount"));
		result.setCurrency((String) map.get("currency"));
		result.setExpiration(LocalDateTime.parse((String)map.get("expiration"), DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss")));
		result.setDescription((String) map.get("description"));
		return result;
	}

	private List<GiftCard> generateGiftCardList(int count, Double amount, LocalDateTime before, LocalDateTime after) {
		List<GiftCard> result = new ArrayList<>();
		for(int i = 0; i < count; i++) {
			result.add(new GiftCard(null, amount, "EUR", i % 2 == 0 ? before : after));
		}
		return result;
	}

	@BeforeEach
	private void clearAll() {
		giftCardDAO.clear();
	}

}
