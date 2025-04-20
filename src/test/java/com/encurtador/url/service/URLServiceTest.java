package com.encurtador.url.service;

import com.encurtador.url.model.URL;
import com.encurtador.url.model.URLMock;
import com.encurtador.url.repositories.URLRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class URLServiceTest {
    URLMock input = new URLMock();

    @InjectMocks
    private URLService urlService;

    @Mock
    private URLRepository urlRepository;

    @Test
    void shouldReduceURL() {
        String url = "https://www.exemplo.com/exemplos-de-url-longas?parame=valor&outroparam=valor2";
        String result = urlService.reduceURL(url);

        assertAll("URL Reduction Assertions",
                () -> assertNotNull(result),
                () -> assertNotEquals(url, result),
                () -> assertTrue(result.startsWith("http://myencurter.com/")),
                () -> assertTrue(result.length() < url.length())
        );
    }

    @Test
    void shouldReturnExistingShortURL() {
        String shortCode = "abc123";
        URL mockUrl = new URL(1L, "https://www.google.com", shortCode);

        when(urlRepository.findByNewUrl(shortCode)).thenReturn(Optional.of(mockUrl));
        String result = urlService.redirectURL(shortCode);

        assertAll("Existing Short URL Assertions",
                () -> assertNotNull(result),
                () -> assertEquals(mockUrl.getUrl(), result),
                () -> assertTrue(result.startsWith("https://www.google.com"))
        );
    }
}
