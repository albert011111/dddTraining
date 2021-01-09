package com.kruczek.email;

import java.util.Set;

interface EmailQueryRepository {
	long count();
	//findBy name is required to avoid searching for property name after By keyword ...
	<T> Set<T> findBy(Class<T> clazz);
}
