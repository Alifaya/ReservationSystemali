package sem451;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReserveBlockList {

    // A list to store the ReserveBlock objects
    private List<ReserveBlock> reserveBlocks;

    /**
     * Constructor to initialize the ReserveBlockList.
     */
    public ReserveBlockList() {
        reserveBlocks = new ArrayList<>();
    }

    public void addReserveBlock(ReserveBlock rb) {
        if (rb != null) {
            reserveBlocks.add(rb);
        }
    }

    public boolean removeReserveBlock(ReserveBlock rb) {
        if (rb != null) {
            return reserveBlocks.remove(rb);
        }
        return false;
    }

    public List<ReserveBlock> findReserveBlocksByDate(LocalDate date) {
        List<ReserveBlock> result = new ArrayList<>();
        for (ReserveBlock rb : reserveBlocks) {
            if (rb.getDate().equals(date)) {
                result.add(rb);
            }
        }
        return result;
    }

    public List<LocalDate> findDatesForReservedBlock(ReserveBlock targetBlock) {
        List<LocalDate> matchingDates = new ArrayList<>();
        for (ReserveBlock rb : reserveBlocks) {
            if (rb.equals(targetBlock)) {
                matchingDates.add(rb.getDate());
            }
        }
        return matchingDates;
    }

    public List<ReserveBlock> getAllReserveBlocks() {
        return new ArrayList<>(reserveBlocks);
    }
}
