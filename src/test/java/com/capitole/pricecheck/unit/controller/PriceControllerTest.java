package com.capitole.pricecheck.unit.controller;

import com.capitole.pricecheck.controller.PriceController;
import com.capitole.pricecheck.exception.PriceNotFoundException;
import com.capitole.pricecheck.service.PriceService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PriceControllerTest {

    @Autowired
    private PriceService priceService;

    private MockMvc mockMvc;
    private String urlTemplate;
    private String paramName;

    @BeforeAll
    public void beforeAll() {
        mockMvc = MockMvcBuilders.standaloneSetup(new PriceController(priceService)).build();

        urlTemplate = "/brands/{brandId}/products/{productId}";
        paramName = "applicationDate";
    }

    @Test
    public void testGetPriceByApplicationDate_WhenTest1_ShouldReturnOK_Price35_50() throws Exception {

        String brandId = "1";
        String productId = "35455";
        String applicationDate = "2020-06-14T10:00:00";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(urlTemplate, brandId, productId)
                .param(paramName, applicationDate)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(35.50));
    }

    @Test
    public void testGetPriceByApplicationDate_WhenTest2_ShouldReturnOK_Price25_45() throws Exception {

        String brandId = "1";
        String productId = "35455";
        String applicationDate = "2020-06-14T16:00:00";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(urlTemplate, brandId, productId)
                .param(paramName, applicationDate)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(25.45));
    }

    @Test
    public void testGetPriceByApplicationDate_WhenTest3_ShouldReturnOK_Price35_50() throws Exception {

        String brandId = "1";
        String productId = "35455";
        String applicationDate = "2020-06-14T21:00:00";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(urlTemplate, brandId, productId)
                .param(paramName, applicationDate)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(35.50));
    }

    @Test
    public void testGetPriceByApplicationDate_WhenTest4_ShouldReturnOK_Price30_50() throws Exception {

        String brandId = "1";
        String productId = "35455";
        String applicationDate = "2020-06-15T10:00:00";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(urlTemplate, brandId, productId)
                .param(paramName, applicationDate)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(30.50));
    }

    @Test
    public void testGetPriceByApplicationDate_WhenTest5_ShouldReturnOK_Price38_95() throws Exception {

        String brandId = "1";
        String productId = "35455";
        String applicationDate = "2020-06-16T21:00:00";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(urlTemplate, brandId, productId)
                .param(paramName, applicationDate)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(38.95));
    }

    @Test
    public void testGetPriceByApplicationDate_WhenProductIdNotExist_ShouldThrowPriceNotFoundException() throws Exception {

        String brandId = "1";
        String productIdNotExist = "99999999999999";
        String applicationDate = "2020-06-16T21:00:00";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(urlTemplate, brandId, productIdNotExist)
                .param(paramName, applicationDate)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        ResultActions resultActions = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        assertEquals(PriceNotFoundException.class, resultActions.andReturn().getResolvedException().getClass());
    }

    @Test
    public void testGetPriceByApplicationDate_WhenBrandIdNotExist_ShouldThrowPriceNotFoundException() throws Exception {

        String brandIdNotExist = "999999999";
        String productId = "35455";
        String applicationDate = "2020-06-16T21:00:00";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(urlTemplate, brandIdNotExist, productId)
                .param(paramName, applicationDate)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        ResultActions resultActions = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        assertEquals(PriceNotFoundException.class, resultActions.andReturn().getResolvedException().getClass());
    }

    @Test
    public void testGetPriceByApplicationDate_WhenApplicationDateNotApply_ShouldThrowPriceNotFoundException() throws Exception {

        String brandId = "1";
        String productId = "35455";
        String applicationDateNotApply = "2024-01-01T00:00:00";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(urlTemplate, brandId, productId)
                .param(paramName, applicationDateNotApply)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        ResultActions resultActions = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        assertEquals(PriceNotFoundException.class, resultActions.andReturn().getResolvedException().getClass());
    }

    @Test
    public void testGetPriceByApplicationDate_WhenApplicationDateFormatNotApply_ShouldThrowMethodArgumentTypeMismatchException() throws Exception {

        String brandId = "1";
        String productId = "35455";
        String applicationDateNotApply = "2024-01-01T00:00:0022222222";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(urlTemplate, brandId, productId)
                .param(paramName, applicationDateNotApply)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        ResultActions resultActions = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        assertEquals(MethodArgumentTypeMismatchException.class, resultActions.andReturn().getResolvedException().getClass());
    }

    @Test
    public void testGetPriceByApplicationDate_WhenProductIdFormatNotValid_ShouldThrowMethodArgumentTypeMismatchException() throws Exception {

        String brandId = "1";
        String productIdFormatNotValid = "XYZ";
        String applicationDate = "2024-01-01T00:00:0022222222";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(urlTemplate, brandId, productIdFormatNotValid)
                .param(paramName, applicationDate)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        ResultActions resultActions = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        assertEquals(MethodArgumentTypeMismatchException.class, resultActions.andReturn().getResolvedException().getClass());
    }

    @Test
    public void testGetPriceByApplicationDate_WhenBrandIdFormatNotValid_ShouldThrowMethodArgumentTypeMismatchException() throws Exception {

        String brandIdFormatNotValid = "XYZ";
        String productId = "35455";
        String applicationDate = "2024-01-01T00:00:0022222222";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(urlTemplate, brandIdFormatNotValid, productId)
                .param(paramName, applicationDate)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        ResultActions resultActions = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        assertEquals(MethodArgumentTypeMismatchException.class, resultActions.andReturn().getResolvedException().getClass());
    }

    @Test
    public void testGetPriceByApplicationDate_WhenParamApplicationDateNotExist_ShouldThrowMissingServletRequestParameterException() throws Exception {

        String brandId = "1";
        String productId = "35455";
        String applicationDate = "2024-01-01T00:00:0022222222";
        String paramApplicationDateNotExist = "xyz";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(urlTemplate, brandId, productId)
                .param(paramApplicationDateNotExist, applicationDate)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        ResultActions resultActions = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        assertEquals(MissingServletRequestParameterException.class, resultActions.andReturn().getResolvedException().getClass());
    }
}
