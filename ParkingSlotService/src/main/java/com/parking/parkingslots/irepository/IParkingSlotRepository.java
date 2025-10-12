package com.parking.parkingslots.irepository;
import java.util.List;

import com.parking.parkingslots.model.ParkingSlot;

public interface IParkingSlotRepository {

   List<ParkingSlot>getAllParkingSlots();
   ParkingSlot getParkingSlotById(Long slotId);
     boolean isSlotAvailable(String slotNumber);
      void addParkingSlot(ParkingSlot slot);
      void updateSlotAvailability(Long slotId, boolean isAvailable);
}
