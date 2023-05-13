package it.unipd.dei.eis.domain.use_cases;

import it.unipd.dei.eis.core.errors.Failure;
import it.unipd.dei.eis.core.utils.Either;
import it.unipd.dei.eis.data.serialization.JsonEncoder;
import it.unipd.dei.eis.domain.models.Article;
import it.unipd.dei.eis.domain.models.IModel;
import it.unipd.dei.eis.domain.repositories.RepositoryFactory;
import org.apache.commons.cli.CommandLine;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ArticlesDownloader extends UseCase {

    public ArticlesDownloader(CommandLine cmd) {
        super(cmd);
        repository = new RepositoryFactory(cmd).create(cmd.getOptionValue("source"));
    }

    @Override
    @SuppressWarnings("unchecked")
    public void execute() {
        Either<Failure, ? extends List<? extends IModel>> result = repository.fetch(cmd);
        if (result.isFailure()) {
            System.out.println(result.failure.message);
        } else {
            List<Article> articles = (List<Article>) result.success;
            JsonEncoder jsonEncoder = new JsonEncoder();
            try (FileWriter fileWriter = new FileWriter(cmd.hasOption("output") ? cmd.getOptionValue("output") : "articles.json")) {
                fileWriter.write(jsonEncoder.encode(articles));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
