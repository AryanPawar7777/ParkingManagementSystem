package com.parking.parkingslots.irepository;
import java.util.List;

import com.parking.parkingslots.model.ParkingSlot;

public interface IParkingSlotRepository {

   List<ParkingSlot>getAllParkingSlots();
   ParkingSlot getParkingSlotById(Long slotId);
     boolean isSlotAvailable(String slotNumber);
      void addParkingSlot(ParkingSlot slot);
      void updateSlotAvailability(Long slotId, boolean isAvailable);
      void deleteParkingSlot(Long slotId);


       List<ParkingSlot> getParkingSlotsByPage(int offset, int limit);

           int getTotalParkingSlotsCount();

}
