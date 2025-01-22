import java.util.Iterator;
import java.util.List;

public class DessinIterator implements Iterator<ComposantDessin> {
    private List<ComposantDessin> enfants;
    private int position = 0;

    public DessinIterator(List<ComposantDessin> enfants) {
        this.enfants = enfants;
    }

    @Override
    public boolean hasNext() {
        return position < enfants.size();
    }

    @Override
    public ComposantDessin next() {
        return enfants.get(position++);
    }
}
