package org.example;

import java.util.ArrayList;
import java.util.List;

public class InternetPackageService {

    private final List<InternetPackageModel> packages = new ArrayList<>();

    public void save(InternetPackageModel model) {
        packages.add(model);
    }

    public List<InternetPackageModel> show() {
        return packages;
    }

    public void deleteById(int id) {
        packages.remove(id);

    }
}
