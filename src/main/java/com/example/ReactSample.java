package com.example;

import java.util.Arrays;
import java.util.List;

import reactor.core.publisher.Flux;

public class ReactSample {
	public static void main(String[] args) {
		final List<String> basket1 = Arrays.asList(new String[]{"kiwi", "orange", "lemon", "orange", "lemon", "kiwi"});
	    final List<String> basket2 = Arrays.asList(new String[]{"banana", "lemon", "lemon", "kiwi"});
	    final List<String> basket3 = Arrays.asList(new String[]{"strawberry", "orange", "lemon", "grape", "strawberry"});
	    final List<List<String>> baskets = Arrays.asList(basket1, basket2, basket3);
	    final Flux<List<String>> basketFlux = Flux.fromIterable(baskets);
	}
}
