package vn.blu.tvviem.loansys.controllers.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Java6Assertions;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import vn.blu.tvviem.loansys.models.taisan.LoaiTaiSan;
import vn.blu.tvviem.loansys.services.protocol.LoaiTaiSanService;
import vn.blu.tvviem.loansys.web.error.ErrorResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@ExtendWith(SpringExtension.class)
// @WebAppConfiguration
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = LoaiTaiSanRest.class, secure = false)
class LoaiTaiSanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Giup sử dụng cấu hình sourcecode, bo qua cau hinh bao mat
    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private LoaiTaiSanService loaiTaiSanService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() { // Lấy cấu hình exclude = { SecurityAutoConfiguration.class } từ main class
        //skip HTTP_403 authenticate OR using secure = false
        //mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void whenValidUrlAndMethodAndContentType_thenReturns200() throws Exception {
        LoaiTaiSan loaiTaiSan = new LoaiTaiSan("Xe máy", "Loại xe thô sơ, có động cơ");
        mockMvc.perform(post("/loaitaisans")
                .content(objectMapper.writeValueAsString(loaiTaiSan))
                .contentType("application/json"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void whenValidInput_thenMapsToBusinessModel() throws Exception {
        LoaiTaiSan loaiTaiSan = new LoaiTaiSan("Xe máy", "Loại xe thô sơ, có động cơ");

        mockMvc.perform(post("/loaitaisans")
                .content(objectMapper.writeValueAsString(loaiTaiSan))
                .contentType("application/json"))
                .andExpect(status().isOk());

        ArgumentCaptor<LoaiTaiSan> loaiTaiSanCaptor = ArgumentCaptor.forClass(LoaiTaiSan.class);
        verify(loaiTaiSanService, times(1)).saveLoaiTs(loaiTaiSanCaptor.capture());

        // JUnit 5
        assertThat(loaiTaiSanCaptor.getValue().getTenLoai(), CoreMatchers.containsString("Xe máy"));
        assertThat(loaiTaiSanCaptor.getValue().getGhiChu(), CoreMatchers.containsString("Loại xe thô sơ, có động cơ"));
    }

    @Test
    void whenValidInput_thenReturnsLoaiTaiSanResource() throws Exception {

        LoaiTaiSan loaiTaiSan = new LoaiTaiSan("Xe máy", "Loại xe thô sơ, có động cơ");

        when(loaiTaiSanService.saveLoaiTs(any(LoaiTaiSan.class))).thenReturn(loaiTaiSan);
        MvcResult mvcResult = mockMvc.perform(post("/loaitaisans")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(loaiTaiSan)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tenLoai").value(loaiTaiSan.getTenLoai()))
                .andReturn();

        LoaiTaiSan expectedResponseBody = loaiTaiSan;
        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        Java6Assertions.assertThat(objectMapper.writeValueAsString(expectedResponseBody)).isEqualToIgnoringWhitespace(actualResponseBody);
        // assertSame("Hai doi tuong ko phu hop", expectedResponseBody, actualResponseBody);
    }

    @Test
    void whenNullValue_thenReturns400() throws Exception {

        LoaiTaiSan user = new LoaiTaiSan("", "zaphod@galaxy.net");

        mockMvc.perform(post("/loaitaisans")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenNullValue_thenReturns400AndErrorResult() throws Exception {

        LoaiTaiSan user = new LoaiTaiSan(null, "zaphod@galaxy.net");

        MvcResult mvcResult = mockMvc.perform(post("/loaitaisans")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResult expectedErrorResponse = new ErrorResult("tenLoai", "must not be null");
        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        String expectedResponseBody = objectMapper.writeValueAsString(expectedErrorResponse);
        Java6Assertions.assertThat(expectedResponseBody).isEqualToIgnoringWhitespace(actualResponseBody);
    }

    @Test
    void getOneLoaiTaiSanTest() throws Exception {
        LoaiTaiSan loaiTaiSan = new LoaiTaiSan("Xe máy", "Loại xe thô sơ, có động cơ");

        when(loaiTaiSanService.getOneLoaiTs(anyInt())).thenReturn(loaiTaiSan);

        MvcResult mvcResult = mockMvc.perform(get("/loaitaisans/{id}/", 0L)
                .contentType("application/json")).andExpect(status().isOk()).andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
        assertEquals(objectMapper.writeValueAsString(loaiTaiSan), mvcResult.getResponse().getContentAsString());

    }
    @Test
    void getAllLoaiTaiSanTest() throws Exception {
        List<LoaiTaiSan> fakeListLoaiTaiSan = buildListLoaiTaiSan();
        when(loaiTaiSanService.getAllLoaiTaiSan()).thenReturn(fakeListLoaiTaiSan);

        MvcResult mvcResult =
                mockMvc.perform(get("/loaitaisans/").contentType("application/json")).andExpect(status().isOk()).andReturn();
        // verify status
        int status = mvcResult.getResponse().getStatus();
        assertEquals("Incorrect Status code", HttpStatus.OK.value(), status);

        // verify size of List
        ObjectMapper objectMapper = new ObjectMapper();
        List<LoaiTaiSan> actualListOfObject = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                new TypeReference<List<LoaiTaiSan>>(){});
        assertEquals("Kich co CHUA PHU HOP", fakeListLoaiTaiSan.size(), actualListOfObject.size());
    }

    private List<LoaiTaiSan> buildListLoaiTaiSan() {
        LoaiTaiSan loaiTaiSan1 = new LoaiTaiSan("Xe máy", "Loại xe có động cơ thô sơ");
        LoaiTaiSan loaiTaiSan2 = new LoaiTaiSan("Xe oto", "Xe 4 bánh chở nhiều người");
        LoaiTaiSan loaiTaiSan3 = new LoaiTaiSan("Nhà đất", "Nhà ở hoặc trồng trọt");

        return Arrays.asList(loaiTaiSan1, loaiTaiSan2, loaiTaiSan3);
    }

}