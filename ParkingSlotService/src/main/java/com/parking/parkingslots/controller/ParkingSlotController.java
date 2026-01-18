package com.parking.parkingslots.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.http.ResponseEntity;

import com.parking.parkingslots.service.ParkingSlotService;
import com.parking.parkingslots.dto.ParkingSlotDto;
import com.parking.parkingslots.dto.PaginatedResponse;

@RestController
@RequestMapping("/slots")
public class ParkingSlotController {

    private final ParkingSlotService parkingSlotService;

    @Autowired
    public ParkingSlotController(ParkingSlotService parkingSlotService) {
        this.parkingSlotService = parkingSlotService;
    }

    // GET all slots
    @GetMapping("/all")
    public List<ParkingSlotDto> getAllSlots() {
        System.out.println("Fetching all parking slots...");
        return parkingSlotService.getAllParkingSlots();
    }

    // GET slot by ID
    @GetMapping("/{slotId}")
    public ParkingSlotDto getSlotById(@PathVariable("slotId") Long slotId) {
        System.out.println("Fetching slot with ID: " + slotId);
        return parkingSlotService.getSlotById(slotId);
    }

    // Check availability by slot number
    @GetMapping("/check")
    public boolean checkAvailability(@RequestParam("slotNo") String slotNumber) {
        System.out.println("Checking availability for Slot No: " + slotNumber);
        return parkingSlotService.checkAvailability(slotNumber);
    }

    // Add a new parking slot
    @PostMapping("/add")
    public ParkingSlotDto addSlot(@RequestBody ParkingSlotDto slotDto) {
        System.out.println("Adding Slot => No: " + slotDto.getSlotNumber());
        parkingSlotService.addSlot(slotDto);
        return slotDto; // Return the added DTO
    }

    // Update availability
    @PutMapping("/update/{slotId}")
    public ParkingSlotDto updateAvailability(@PathVariable("slotId") Long slotId,
                                             @RequestParam("availability") boolean availability) {
        System.out.println("Updating Slot ID " + slotId + " to " + availability);
        parkingSlotService.updateAvailability(slotId, availability);

        // Return updated slot DTO
        return parkingSlotService.getSlotById(slotId);
    }


@DeleteMapping("/delete/{slotId}")
public String deleteSlot(@PathVariable("slotId") Long slotId) {
    parkingSlotService.deleteSlot(slotId);
    return "Slot with ID " + slotId + " deleted successfully.";
}


@GetMapping("/nextpage")
public ResponseEntity<PaginatedResponse<ParkingSlotDto>> getPaginatedSlots(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(required = false) Integer size
) {
    PaginatedResponse<ParkingSlotDto> response = parkingSlotService.getPaginatedSlots(page, size);
    return ResponseEntity.ok(response);
}

}
