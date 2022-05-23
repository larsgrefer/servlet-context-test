/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package de.larsgrefer.test;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import javax.servlet.ServletContext;
import java.io.File;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testResources() {
        String resources = restTemplate.getForObject("/resources", String.class);

        List<String> collect = Arrays.stream(resources.split("\n"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());

        assertThat(collect).contains(
                "/classpath-resource.txt",
                "/library-classpath-resource.txt",
                "/test-classpath-resource.txt"
        );

        if (new File(".").getAbsolutePath().contains("-war")) {
            assertThat(collect).contains(
                    "/war-resource.txt"
            );
        }
    }

    @Autowired
    private ServletContext servletContext;

    @Test
    public void testDirectAccess() throws MalformedURLException {
        assertThat(servletContext.getResource("/classpath-resource.txt")).isNotNull();
        assertThat(servletContext.getResource("/library-classpath-resource.txt")).isNotNull();
        assertThat(servletContext.getResource("/test-classpath-resource.txt")).isNotNull();
        assertThat(servletContext.getResource("/war-resource.txt")).isNotNull();
    }
}
