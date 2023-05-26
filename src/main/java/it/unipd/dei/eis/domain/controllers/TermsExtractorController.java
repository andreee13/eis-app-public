package it.unipd.dei.eis.domain.controllers;

import it.unipd.dei.eis.core.constants.UseCaseConstants;
import it.unipd.dei.eis.core.constants.DefaultSettings;
import it.unipd.dei.eis.core.utils.Either;
import it.unipd.dei.eis.core.utils.Failure;
import it.unipd.dei.eis.core.utils.Success;
import it.unipd.dei.eis.domain.models.IModel;
import it.unipd.dei.eis.domain.repositories.RepositoryFactory;
import it.unipd.dei.eis.presentation.Context;

import java.util.List;

/**
 * TermsExtractorController is the controller for the terms extraction use case.
 * It is used to extract terms from the data source.
 */
public class TermsExtractorController extends Controller {

    /**
     * TermsExtractorController constructor.
     */
    public TermsExtractorController() {
        super(UseCaseConstants.EXTRACT);
    }

    /**
     * Executes the terms extraction use case.
     *
     * @param context the context
     * @return the result of the use case
     */
    @Override
    @SuppressWarnings("unchecked, rawtypes")
    public Either<Failure, Success> execute(Context context) {
        String source = context.command.equals(UseCaseConstants.BOTH) ? DefaultSettings.OUTPUT_FILE_JSON : context.source;
        System.out.println("Reading data from " + source + "...");
        Either<Failure, ? extends List<? extends IModel>> result1 = RepositoryFactory.create(source)
                .pull(context);
        if (result1.isFailure()) {
            return Either.failure(result1.failure);
        }
        System.out.println("Read " + (result1.success != null ? result1.success.size() : 0) + " items");
        System.out.println("Extracting terms...");
        RepositoryFactory.create(UseCaseConstants.EXTRACT)
                .push(context, (List) result1.success);
        System.out.println("Terms extracted and written to " + (context.output != null ? context.output : DefaultSettings.OUTPUT_FILE_TXT));
        return Either.success(new Success());
    }
}
