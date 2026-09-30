package ma.xproce.videoservice.exceptions;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GraphQLExceptionHandler extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        return new GraphQLError() {

            @Override
            public String getMessage() {
                return ex.getMessage();
            }

            @Override
            public List<SourceLocation> getLocations() {
                return List.of(env.getField().getSourceLocation());
            }

            @Override
            public ErrorClassification getErrorType() {
                return ErrorType.NOT_FOUND;
            }

            @Override
            public List<Object> getPath() {
                return env.getExecutionStepInfo().getPath().toList();
            }
        };
    }
}