package AimsProject.Src.hust.soict.dsai.aims.media;

import java.util.Comparator;

public class MediaComparatorByTitleCost implements Comparator<Media> {
    @Override
    public int compare(Media m1, Media m2) {
        // So sánh theo tiêu đề (alphabet)
        int titleDiff = m1.getTitle().compareToIgnoreCase(m2.getTitle());
        if (titleDiff != 0) {
            return titleDiff;
        }
        // Nếu tiêu đề giống nhau, sắp xếp theo giá giảm dần
        return Float.compare(m2.getCost(), m1.getCost());
    }
}