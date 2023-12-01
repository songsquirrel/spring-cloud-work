package com.song.basicx.utils;

import io.micrometer.common.util.StringUtils;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.Assert;

/**
 * spel实例化类
 */
public class SpelUtil {

    private static final SpelExpressionParser parser = new SpelExpressionParser();

    private SpelUtil() {
    }

    public static SpelExpressionParser getSingleInstance() {
        return parser;
    }

    public static <T, R> T build(String pattern, String varName, R varVal, Class<T> clazz) {
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable(varName, varVal);
        return parser.parseExpression(pattern).getValue(context, clazz);
    }
}
