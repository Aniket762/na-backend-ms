package aniket762.nyayAssist.controller;

import aniket762.nyayAssist.service.GithubLawLoaderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScrapperController {

    private final GithubLawLoaderService loader;

    public ScrapperController(GithubLawLoaderService loader) {
        this.loader = loader;
    }

    @GetMapping("/load/ipc")
    public String loadIPC() {
        loader.loadLaw(GithubLawLoaderService.LawSource.IPC, "ipc_dataset.json");
        return "IPC loading started";
    }

    @GetMapping("/load/mva")
    public String loadMVA() {
        loader.loadLaw(GithubLawLoaderService.LawSource.MVA, "mva_dataset.json");
        return "MVA loading started";
    }

    @GetMapping("/load/cpc")
    public String loadCPC() {
        new Thread(() -> loader.loadLaw(GithubLawLoaderService.LawSource.CPC, "cpc_dataset.json")).start();
        return "CPC loading started";
    }

    @GetMapping("/load/hma")
    public String loadHMA() {
        new Thread(() -> loader.loadLaw(GithubLawLoaderService.LawSource.HMA, "hma_dataset.json")).start();
        return "HMA loading started";
    }

    @GetMapping("/load/iea")
    public String loadIEA() {
        new Thread(() -> loader.loadLaw(GithubLawLoaderService.LawSource.IEA, "iea_dataset.json")).start();
        return "IEA loading started";
    }

    @GetMapping("/load/nia")
    public String loadNIA() {
        new Thread(() -> loader.loadLaw(GithubLawLoaderService.LawSource.NIA, "nia_dataset.json")).start();
        return "NIA loading started";
    }

    @GetMapping("/load/all")
    public String loadAll() {
        new Thread(() -> {
            for (GithubLawLoaderService.LawSource source : GithubLawLoaderService.LawSource.values()) {
                loader.loadLaw(source, source.name().toLowerCase() + "_dataset.json");
            }
        }).start();
        return "All laws loading started";
    }
}
