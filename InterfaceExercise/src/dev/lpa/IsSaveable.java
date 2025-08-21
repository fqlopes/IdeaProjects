package dev.lpa;

import java.util.List;

public interface IsSaveable {
    List<String> write();
    void read(List<String> saveState);
}
