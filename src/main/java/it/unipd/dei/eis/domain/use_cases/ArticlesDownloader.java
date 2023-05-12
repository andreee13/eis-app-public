package it.unipd.dei.eis.domain.use_cases;

import it.unipd.dei.eis.core.errors.Failure;
import it.unipd.dei.eis.core.utils.Either;
import it.unipd.dei.eis.data.serialization.JsonEncoder;
import it.unipd.dei.eis.data.sources.DataSource;
import it.unipd.dei.eis.domain.models.Article;
import it.unipd.dei.eis.domain.repositories.CsvRepository;
import it.unipd.dei.eis.domain.repositories.Repository;
import it.unipd.dei.eis.domain.repositories.TheGuardianRepository;
import org.apache.commons.cli.CommandLine;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ArticlesDownloader extends UseCase {

    public ArticlesDownloader(CommandLine cmd) {
        super(cmd);
        repository = resolveRepository();
    }

    private Repository<? extends DataSource> resolveRepository() {
        if (cmd.getOptionValue("source").equals("theguardian")) {
            return new TheGuardianRepository();
        } else if (cmd.getOptionValue("source").endsWith(".csv")) {
            return new CsvRepository();
        } else {
            return null;
        }
    }

    @Override
    public void execute() {
        Either<Failure, List<Article>> result = repository.fetch(cmd);
        if (result.isFailure()) {
            System.out.println(result.getFailure().getMessage());
        } else {
            List<Article> articles = result.getSuccess();
            JsonEncoder jsonEncoder = new JsonEncoder();
            StringBuilder stringBuilder = new StringBuilder("{ \"articles\": [");
            for (Article article : articles) {
                stringBuilder.append(jsonEncoder.encode(article));
                stringBuilder.append(",");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.append("]}");
            try (FileWriter fileWriter = new FileWriter(cmd.hasOption("output") ? cmd.getOptionValue("output") : "articles.json")) {
                fileWriter.write(stringBuilder.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
