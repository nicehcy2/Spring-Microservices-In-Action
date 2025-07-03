package com.optimagrowth.license.controller;

import com.google.gson.Gson;
import com.optimagrowth.license.model.License;
import com.optimagrowth.license.service.LicenseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class LicenseControllerTest {

    @Mock
    private LicenseService licenseService;

    @InjectMocks
    private LicenseController licenseController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc =  MockMvcBuilders.standaloneSetup(licenseController).build();
    }

    @DisplayName("라이센스 데이터를 조회하는 GET 컨트롤러")
    @Test
    void getLicenseControllerTest() throws Exception {

        // given
        String organization = "0";
        String licenseId = "0";
        String url = "/v1/organization/" + organization + "/license/" + licenseId;

        License response = new License();
        setResponse(organization, licenseId, response);

        // when
        Mockito.when(licenseService.getLicense(licenseId, organization))
                .thenReturn(response);

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get(url)
                        .accept(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.licenseId").value("0"));
        Mockito.verify(licenseService, Mockito.times(1)).getLicense(licenseId, organization);
    }

    private static void setResponse(String organization, String licenseId, License response) {
        response.setId(1);
        response.setLicenseId(licenseId);
        response.setDescription("Software product");
        response.setOrganizationId(organization);
        response.setProductName("Ostock");
        response.setLicenseType("full");
    }
}