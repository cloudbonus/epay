package com.github.epay.config.kafka;

import com.github.epay.domain.enums.BlogKafkaTopic;
import com.github.epay.domain.exception.ExceptionEnum;
import com.github.epay.domain.exception.impl.CustomException;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;
import java.util.Optional;

/**
 * @author Raman Haurylau
 */
@Setter
@ConfigurationProperties("topic")
public class KafkaTopicProperties {
    private Map<BlogKafkaTopic, String> names;

    public String getTopic(BlogKafkaTopic blogKafkaTopic) {
        return Optional.ofNullable(names.get(blogKafkaTopic))
                .orElseThrow(() -> new CustomException(ExceptionEnum.ENDPOINT_EXCEPTION));
    }
}
