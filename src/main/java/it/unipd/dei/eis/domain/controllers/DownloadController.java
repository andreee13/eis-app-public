package it.unipd.dei.eis.domain.controllers;

import it.unipd.dei.eis.core.constants.DefaultSettings;
import it.unipd.dei.eis.core.constants.UseCaseConstants;
import it.unipd.dei.eis.core.utils.Either;
import it.unipd.dei.eis.core.utils.Failure;
import it.unipd.dei.eis.core.utils.Success;
import it.unipd.dei.eis.domain.models.IModel;
import it.unipd.dei.eis.domain.repositories.RepositoryFactory;
import it.unipd.dei.eis.presentation.Context;

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
        super(UseCaseConstants.DOWNLOAD);
    }

    /**
     * The execute method is used to retrieve data from a source and write them to a file.
     *
     * @param context the context
     * @return the result of the operation
     */
    @Override
    public Either<Failure, Success> execute(Context context) {
        System.out.println("Retrieving data from " + context.source + "...");
        Either<Failure, List<IModel>> result1 = RepositoryFactory.create(context.source)
                .pull(context);
        if (result1.isFailure()) {
            return Either.failure(result1.failure);
        }
        System.out.println("Retrieved " + (result1.success != null ? result1.success.size() : 0) + " items");
        String source = context.output != null ? String.format("%s.json", context.output) : DefaultSettings.JSON_FILE_NAME;
        System.out.println("Writing data to " + source + "...");
        Either<Failure, Success> result2 = RepositoryFactory.create(source)
                .push(context, result1.success);
        if (result2.isFailure()) {
            return Either.failure(result2.failure);
        }
        System.out.println("File written successfully");
        return Either.success(new Success());
    }
}
