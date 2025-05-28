package com.empacotamento;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
class EmpacotamentoApplicationTests {

	@Test
	void contextLoads() {
		EmpacotamentoApplication.main(new String[] {});
	}

}
