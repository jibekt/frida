package be.vdab.frida.repositories;

import be.vdab.frida.domain.Saus;
import be.vdab.frida.exceptions.SausRepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Qualifier("properties")
public class PropertiesSausRepository implements SausRepository {
    private static final Path PAD = Paths.get("/data/sauzen.properties");
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<Saus> findAll() {
        try {
            return Files.lines(PAD)
                    .filter(regel -> ! regel.isEmpty())
                    .map(regel -> maakSaus(regel))
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            String fout = "Fout bij lezen " + PAD;
            logger.error(fout, ex);
            throw new SausRepositoryException(fout);
        }
    }
    private Saus maakSaus(String regel) {
        String[] onderdelen = regel.split(":");
        if (onderdelen.length < 2) {
            String fout = PAD + ":" + regel + " bevat minder dan 2 onderdelen";
            logger.error(fout);
            throw new SausRepositoryException(fout);
        }
        try {
            String[] naamEnIngredienten = onderdelen[1].split(",");
            String[] ingredienten = new String[onderdelen.length - 1];
            for (int index = 1; index < onderdelen.length; index++) {
                ingredienten[index - 1] = onderdelen[index];
            }
            return new Saus(Long.parseLong(onderdelen[0]), naamEnIngredienten[0],
                    ingredienten);
        } catch (NumberFormatException ex) {
            String fout = PAD + ":" + regel + " bevat verkeerde id";
            logger.error(fout, ex);
            throw new SausRepositoryException(fout);
        }
    }
}
