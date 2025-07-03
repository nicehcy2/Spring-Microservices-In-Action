package com.optimagrowth.license.service;

import com.optimagrowth.license.model.License;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LicenseServiceTest {

    private final LicenseService licenseService = new LicenseService();

    @DisplayName("라이센스 ID와 조직 ID를 주면 라이센스 정보를 반환한다.")
    @Test
    void getLicenseTest() {

        // given
        String licenseId = "0"; // 라이센스 ID
        String organizationId = "0"; // 조직 ID

        // when
        License license = licenseService.getLicense(licenseId, organizationId);

        // then
        assertThat(license.getLicenseId()).isEqualTo(licenseId);
        assertThat(license.getOrganizationId()).isEqualTo(organizationId);
    }

    @DisplayName("라이센스와 조직 ID를 주면 라이센스 응답 메세지를 반환한다.")
    @Test
    void createLicenseMessageTest() {

        // given
        String licenseId = "0"; // 라이센스 ID
        String organizationId = "1"; // 조직 ID
        License license = licenseService.getLicense(licenseId, organizationId);

        // when
        organizationId = "20"; // 조직 ID 변경
        String responseMessage = licenseService.createLicense(license, organizationId, null);

        // then
        assertThat(responseMessage).isEqualTo("This is the post and the object is: " + license.toString());
    }

    @DisplayName("라이센스 없이 조직 ID만 줘서 라이센스 응답 메시지를 반환한다.")
    @Test
    void createLicenseMessageWithoutLicenseTest() {

        // given
        License license = null;
        String organizationId = "1";

        // when
        String responseMessage = licenseService.createLicense(license, organizationId, null);

        // then
        assertThat(responseMessage).isNull();
    }
}