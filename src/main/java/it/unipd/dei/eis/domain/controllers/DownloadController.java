package it.unipd.dei.eis.domain.controllers;

import it.unipd.dei.eis.core.common.Context;
import it.unipd.dei.eis.core.common.Either;
import it.unipd.dei.eis.core.common.Failure;
import it.unipd.dei.eis.core.common.Success;
import it.unipd.dei.eis.core.enums.UseCases;
import it.unipd.dei.eis.domain.models.IModel;
import it.unipd.dei.eis.domain.repositories.RepositoryFactory;

import java.util.List;

/**
 * The DownloadController class is the controller for the download use case.
 * It is used to retrieve data from a source and write them to a file.
 */
public class DownloadController extends Controller {

    /**
     * DownloadController constructor.
     */
    public DownloadController() {
        super(String.valueOf(UseCases.DOWNLOAD));
    }

    /**
     * The execute method is used to retrieve data from a source and write them to a file.
     *
     * @param context the context
     * @return Either a Failure or a Success
     */
    @Override
    public Either<Failure, Success> execute(Context context) {
        System.out.println("Retrieving data from " + context.source + "...");
        try {
            List<IModel> result1 = RepositoryFactory.create(context.source)
                    .pull(context);
            System.out.println("Retrieved " + result1.size() + " items");
            System.out.println("Writing data to " + context.outputArticles + "...");
            RepositoryFactory.create(context.outputArticles)
                    .push(context, result1);
            System.out.println("File written successfully");
            return Either.success(new Success());
        } catch (Exception e) {
            return Either.failure(new Failure(e));
        }
    }
}
