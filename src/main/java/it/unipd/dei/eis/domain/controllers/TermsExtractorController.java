package it.unipd.dei.eis.domain.controllers;

import it.unipd.dei.eis.core.common.Context;
import it.unipd.dei.eis.core.common.Either;
import it.unipd.dei.eis.core.common.Failure;
import it.unipd.dei.eis.core.common.Success;
import it.unipd.dei.eis.core.constants.UseCases;
import it.unipd.dei.eis.domain.models.IModel;
import it.unipd.dei.eis.domain.repositories.RepositoryFactory;

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
        super(UseCases.EXTRACT);
    }

    /**
     * Executes the terms extraction use case.
     *
     * @param context the context
     * @return Either a Failure or a Success
     */
    @Override
    @SuppressWarnings("unchecked")
    public Either<Failure, Success> execute(Context context) {
        List<IModel> models;
        try {
            if (context.sharedData.get(UseCases.DOWNLOAD) != null) {
                System.out.println("Reading data from memory...");
                models = (List<IModel>) context.sharedData.get(UseCases.DOWNLOAD);
            } else {
                System.out.println("Reading data from " + context.source + "...");
                models = RepositoryFactory.create(context.source)
                        .pull(context);
            }
            System.out.println("Read " + models.size() + " items");
            System.out.println("Extracting " + context.countTerms + " terms...");
            RepositoryFactory.create(context.outputTerms)
                    .push(context, models);
            System.out.println("Terms extracted and written to ".concat(context.outputTerms));
            return Either.success(new Success());
        } catch (Exception e) {
            return Either.failure(new Failure(e));
        }
    }
}
