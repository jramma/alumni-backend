package cat.api.login.proxy;


import cat.api.login.config.ProxyConfig;
import cat.api.login.dto.AuthenticationRequest;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.ClientCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.net.URI;
import java.net.URL;
import java.time.Duration;

@Component
public class HttpProxy {
	
	public HttpClient httpClient;
	public WebClient webClient;
	
	@Autowired
	public HttpProxy(ProxyConfig config) {
		
		httpClient = HttpClient.create()
			.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, Integer.valueOf(config.getConnection_timeout()))
			.responseTimeout(Duration.ofMillis((long)config.getConnection_timeout()));
		
		webClient = WebClient.builder()
			.clientConnector(new ReactorClientHttpConnector(httpClient))
			.exchangeStrategies(ExchangeStrategies.builder().codecs(this::acceptedCodecs).build())
			.build();
	}
	
	private void acceptedCodecs(ClientCodecConfigurer clientCodecConfigurer) {
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		clientCodecConfigurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(mapper, MediaType.APPLICATION_JSON));
		clientCodecConfigurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(mapper, MediaType.APPLICATION_JSON));
	}
	
	public <T> Mono<T> getRequestData(URL url, String jwt, AuthenticationRequest request, Class<T> clazz) {
		return webClient.method(HttpMethod.GET)
				.uri(URI.create(url.toString()))
				.header(HttpHeaders.AUTHORIZATION, jwt)
				.bodyValue(request)
				.retrieve()
				.bodyToMono(clazz);
	}
	
}