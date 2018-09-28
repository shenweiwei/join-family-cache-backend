package org.sww.joinfamily.cache;

import java.io.File;
import java.io.FileInputStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileControllerTest {
	@Autowired
	private WebApplicationContext context;
	private MockMvc mockMvc;
	private byte[] buffer;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build(); // 构造MockMvc
	}
	@Before
	public void setFile() throws Exception {
		File file = new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\IMG_20161201_192329.png");
		long fileSize = file.length();
		if (fileSize > Integer.MAX_VALUE) {
			System.out.println("file too big...");
		} else {
			FileInputStream fi = new FileInputStream(file);
			buffer = new byte[(int) fileSize];
			int offset = 0;
			int numRead = 0;
			while (offset < buffer.length && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
				offset += numRead;
			}
			// 确保所有数据均被读取
			if (offset == buffer.length) {
				fi.close();
			}
		}
	}
	@Test
	public void upload() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.multipart("/file/upload")
				.file(new MockMultipartFile("file", "test.png", ",multipart/form-data", buffer)))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
	}
	// @Test
	// public void test() throws Exception {
	// mvc.perform(post("/file/upload")//调用接口
	// .contentType(MediaType.APPLICATION_JSON_UTF8).param("userId", "11").param("userName",
	// "henry")// 传入添加的用户参数 
	// .accept(MediaType.APPLICATION_JSON)) // 接收的类型
	// .andExpect(status().isOk()) // 判断接收到的状态是否是200
	// .andDo(print()) // 打印内容
	// .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
	// .andExpect(content().string(Matchers.containsString("OK"))); // 匹配返回值中的内容
	// }
}
