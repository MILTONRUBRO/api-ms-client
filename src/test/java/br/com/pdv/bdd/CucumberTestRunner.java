package br.com.pdv.bdd;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "br.com.pdv.bdd"
)
@AutoConfigureWebTestClient
public class CucumberTestRunner {


}
