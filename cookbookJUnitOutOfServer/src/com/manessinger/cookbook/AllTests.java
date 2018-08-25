package com.manessinger.cookbook;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    com.manessinger.cookbook.service.TestCookbookBean.class,
    com.manessinger.cookbook.eao.TestCookbookEao.class,
})


public class AllTests {

}
