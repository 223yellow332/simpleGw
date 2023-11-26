package com.calmdown.simpleGw.document;

import com.calmdown.simpleGw.controller.GatewayController;
import com.calmdown.simpleGw.domain.MobileCarrier;
import com.calmdown.simpleGw.domain.Orders;
import com.calmdown.simpleGw.dto.AuthRequest;
import com.calmdown.simpleGw.dto.CancelRequest;
import com.calmdown.simpleGw.dto.CertRequest;
import com.calmdown.simpleGw.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WebMvcTest(GatewayController.class)
class GatewayControllerTest extends AbstractRestDocsTests {

    @MockBean
    private OrderService orderService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @Order(1)
    void certSuccessTest() throws Exception {
        //given
        CertRequest request = getCertRequest();
        Orders orders = request.toEntity("20231125TEST0001", 240000L);

        given(orderService.save(any(Orders.class))).willReturn(orders);

        //when
        ResultActions actions = mockMvc
                .perform(post("/v1/pay/cert")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        );

        //then
        MvcResult result = actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultCode").value("0"))
                .andDo(restDocs.document(
                        requestFields(
                                fieldWithPath("phone").type(JsonFieldType.STRING).description("휴대전화번호"),
                                fieldWithPath("mobileCarrier").type(JsonFieldType.STRING).description("통신사코드"),
                                fieldWithPath("payAmount").type(JsonFieldType.NUMBER).description("결제금액")),
                        responseFields(
                                fieldWithPath("mobileTrxid").type(JsonFieldType.STRING).description("SimpleGw 거래번호"),
                                fieldWithPath("limitAmount").type(JsonFieldType.NUMBER).description("사용가능한 한도금액"),
                                fieldWithPath("resultCode").type(JsonFieldType.STRING).description("결과코드"),
                                fieldWithPath("resultMessage").type(JsonFieldType.STRING).description("결과메시지"))
                        )
                )
                .andReturn()
                ;
    }

    @Test
    @Order(2)
    void authSuccessTest() throws Exception {
        //given
        AuthRequest request = getAuthRequest();

        CertRequest certRequest = getCertRequest();
        Orders orders = certRequest.toEntity("20231125TEST0001", 240000L);

        given(orderService.findById(any(String.class))).willReturn(orders);

        //when
        ResultActions actions = mockMvc
                .perform(post("/v1/pay/auth")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                );

        //then
        MvcResult result = actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultCode").value("0"))
                .andDo(restDocs.document(
                                requestFields(
                                        fieldWithPath("mobileTrxid").type(JsonFieldType.STRING).description("SimpleGw 거래번호"),
                                        fieldWithPath("phone").type(JsonFieldType.STRING).description("휴대전화번호"),
                                        fieldWithPath("mobileCarrier").type(JsonFieldType.STRING).description("통신사코드"),
                                        fieldWithPath("payAmount").type(JsonFieldType.NUMBER).description("결제금액")),
                                responseFields(
                                        fieldWithPath("mobileTrxid").type(JsonFieldType.STRING).description("SimpleGw 거래번호"),
                                        fieldWithPath("limitAmount").type(JsonFieldType.NUMBER).description("사용가능한 한도금액"),
                                        fieldWithPath("resultCode").type(JsonFieldType.STRING).description("결과코드"),
                                        fieldWithPath("resultMessage").type(JsonFieldType.STRING).description("결과메시지"))
                        )
                )
                .andReturn()
                ;
    }

    @Test
    @Order(3)
    void cancelSuccessTest() throws Exception {
        //given
        CancelRequest request = getCancelRequest();

        CertRequest certRequest = getCertRequest();
        Orders orders = certRequest.toEntity("20231125TEST0001", 240000L);

        given(orderService.findById(any(String.class))).willReturn(orders);

        //when
        ResultActions actions = mockMvc
                .perform(post("/v1/pay/cancel")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                );

        //then
        MvcResult result = actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultCode").value("0"))
                .andDo(restDocs.document(
                                requestFields(
                                        fieldWithPath("mobileTrxid").type(JsonFieldType.STRING).description("SimpleGw 거래번호"),
                                        fieldWithPath("phone").type(JsonFieldType.STRING).description("휴대전화번호"),
                                        fieldWithPath("mobileCarrier").type(JsonFieldType.STRING).description("통신사코드"),
                                        fieldWithPath("payAmount").type(JsonFieldType.NUMBER).description("결제금액")),
                                responseFields(
                                        fieldWithPath("mobileTrxid").type(JsonFieldType.STRING).description("SimpleGw 거래번호"),
                                        fieldWithPath("limitAmount").type(JsonFieldType.NUMBER).description("사용가능한 한도금액"),
                                        fieldWithPath("resultCode").type(JsonFieldType.STRING).description("결과코드"),
                                        fieldWithPath("resultMessage").type(JsonFieldType.STRING).description("결과메시지"))
                        )
                )
                .andReturn()
                ;
    }

    private CertRequest getCertRequest() {
        return CertRequest.builder()
                .phone("01011111234")
                .mobileCarrier(MobileCarrier.SK)
                .payAmount(1004)
                .build();
    }

    private AuthRequest getAuthRequest() {
        return AuthRequest.builder()
                .mobileTrxid("20231125TEST0001")
                .phone("01011111234")
                .mobileCarrier(MobileCarrier.SK)
                .payAmount(1004)
                .build();
    }

    private CancelRequest getCancelRequest() {
        return CancelRequest.builder()
                .mobileTrxid("20231125TEST0001")
                .phone("01011111234")
                .mobileCarrier(MobileCarrier.SK)
                .payAmount(1004)
                .build();
    }

}