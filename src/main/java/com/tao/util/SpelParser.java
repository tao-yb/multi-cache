package com.tao.util;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author tyb
 * @Description
 * @create 2021-09-16 11:48
 */
public class SpelParser {
    private static final ExpressionParser expressionParser = new SpelExpressionParser(new SpelParserConfiguration(true,
            true));

    private static final EvaluationContext evaluationContext = new StandardEvaluationContext();

    public static String getValue(String key ,String[] parms, Object[] args){
        final Expression expression = expressionParser.parseExpression(key);
        for (int i = 0; i < args.length; i++) {
            evaluationContext.setVariable(parms[i],args[i]);
        }
        return expression.getValue(evaluationContext,String.class);
    }

}
