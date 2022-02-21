package com.zh.interceptor;

import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class SQLLogInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();
        if (target instanceof Executor) {
            Executor executor = (Executor) target;
            MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
            Object parameter = invocation.getArgs()[1];
            BoundSql boundSql = mappedStatement.getBoundSql(parameter);
            String sql = boundSql.getSql();
            List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();

            String sqlTemplate = sql.replace("?", "'%s'") // 问号替换字符串模板符号
                    .replace("\n", "") // 去掉换行符
                    .replaceAll(" +", " "); // 多个连续空格改成一个空格

            MapperMethod.ParamMap parameterObject = (MapperMethod.ParamMap) boundSql.getParameterObject();
            if (parameterObject != null) {
                List<Object> parameterValue = new ArrayList<>(parameterObject.size());
                for (ParameterMapping parameterMapping : parameterMappings) {
                    parameterValue.add(parameterObject.get(parameterMapping.getProperty()));
                }
                System.out.println(String.format(sqlTemplate, parameterValue.toArray()));
            } else {
                System.out.println(sqlTemplate);
            }

        }
        return invocation.proceed();
    }
}
