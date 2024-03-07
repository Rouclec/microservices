package com.rouclec.inventoryservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rouclec.inventoryservice.TestDataUtil;
import com.rouclec.inventoryservice.domains.dto.InventoryDto;
import com.rouclec.inventoryservice.domains.entity.Inventory;
import com.rouclec.inventoryservice.repositories.InventoryRepository;
import com.rouclec.inventoryservice.services.InventoryService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class InventoryControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ObjectMapper objectMapper;

    //todo: test that get inventory returns a list of inventory dto instead of just a boolean
    @Test
    public void testThatIsInStockReturns200AndFalseIfInventoryIsNotInStock() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/inventories/SKU1"))
                .andExpect(
                        MockMvcResultMatchers.status().isOk()
                )
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("false")));
    }


    @Test
    public void testThatIsInStockReturns200AndTrueIfInventoryIsInStock() throws Exception {
        Inventory inventory = TestDataUtil.createTestInventoryA();
        inventoryRepository.save(inventory);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/inventories/SKU1")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("true")));
    }

}
