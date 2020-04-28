package com.example;

import java.util.Arrays;
import java.util.List;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ReactSample {
	
	final static List<String> basket1 = Arrays.asList(new String[]{"kiwi", "orange", "lemon", "orange", "lemon", "kiwi"});
    final static List<String> basket2 = Arrays.asList(new String[]{"banana", "lemon", "lemon", "kiwi"});
    final static List<String> basket3 = Arrays.asList(new String[]{"strawberry", "orange", "lemon", "grape", "strawberry"});
    final static List<List<String>> baskets = Arrays.asList(basket1, basket2, basket3);
    final static Flux<List<String>> basketFlux = Flux.fromIterable(baskets);
	
	public static void main(String[] args) {
		and();
		as();
		block();
		cache();
	}
	
	private static void and() {
		System.out.println("and");
		Mono<List<String>> monoBasket = Flux.fromIterable(basket1).collectList();
		Mono<List<String>> monoBasket2 = Flux.fromIterable(basket2).collectList();
		Mono<Void> monoBasket3 = monoBasket.and(monoBasket2);
		monoBasket3.subscribe(System.out::println);
		System.out.println("end and");
		
	}
	
	private static void as() {
		System.out.println("as");
		Mono<List<String>> monoBasket = Flux.fromIterable(basket1).collectList();		
		Flux<List<String>> flux = monoBasket.as(Flux::from);
		flux.subscribe(System.out::println);
		System.out.println("end as");
		
	}
	
	private static void block() {
		System.out.println("block");
		Mono<List<String>> monoBasket = Flux.fromIterable(basket1).collectList();		
		
		List<String> blocked = monoBasket.block();
		blocked.forEach(System.out::println);
		System.out.println("end block");
	}
	
	private static void cache() {
		System.out.println("cache");
		Mono<List<String>> monoBasket = Flux.fromIterable(basket1).collectList();		
		Mono<List<String>> cachedMonoBasket = monoBasket.cache();
		
		for (int i = 0 ; i < 10 ; i++) {
			cachedMonoBasket.subscribe(System.out::println);
			cachedMonoBasket.subscribe(System.out::println);
		}
		
		System.out.println("end cache");
	}
}
