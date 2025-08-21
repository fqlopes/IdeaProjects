package Section11.AnotherStorageSystem;

import java.util.List;

public interface ISaveable {

    List<String> write();
    void read(List<String> list);
}
