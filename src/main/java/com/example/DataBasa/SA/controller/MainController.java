package com.example.DataBasa.SA.controller;

import com.example.DataBasa.SA.model.*;
import com.example.DataBasa.SA.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private StayRepository stayRepository;

    @Autowired
    private ProcedureRepository procedureRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private TreatmentSessionRepository treatmentSessionRepository;

    @Autowired
    private MedicalExaminationRepository medicalExaminationRepository;

    // Главная страница
    @GetMapping
    public String getIndex() {
        return "index";
    }

    // Управление гостями
    @GetMapping("/guests")
    public String getGuests(Model model) {
        List<Guest> guests = guestRepository.findAll();
        model.addAttribute("guests", guests);
        return "guests";
    }

    @PostMapping("/guests/create")
    @Transactional
    public String saveGuest(@RequestParam("name") String name,
                            @RequestParam("phone") String phone,
                            @RequestParam("email") String email) {
        Guest guest = new Guest();
        guest.setName(name);
        guest.setPhone(phone);
        guest.setEmail(email);
        guestRepository.save(guest);
        return "redirect:/guests";
    }

    @PostMapping("/guests/edit/{id}")
    @Transactional
    public String updateGuest(@PathVariable Long id,
                              @RequestParam("name") String name,
                              @RequestParam("phone") String phone,
                              @RequestParam("email") String email) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid guest Id:" + id));
        guest.setName(name);
        guest.setPhone(phone);
        guest.setEmail(email);
        guestRepository.save(guest);
        return "redirect:/guests";
    }

    @PostMapping("/guests/delete/{id}")
    @Transactional
    public String deleteGuest(@PathVariable Long id) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid guest Id:" + id));
        guestRepository.delete(guest);
        return "redirect:/guests";
    }


    // Управление путевками
    @GetMapping("/stays")
    public String getStays(Model model) {
        List<Stay> stays = stayRepository.findAll();
        List<Guest> guests = guestRepository.findAll();
        List<Room> rooms = roomRepository.findAll();
        model.addAttribute("stays", stays);
        model.addAttribute("guests", guests);
        model.addAttribute("rooms", rooms);
        return "stays";
    }

    @PostMapping("/stays/create")
    @Transactional
    public String saveStay(@ModelAttribute("stay") Stay stay,
                           @RequestParam("guestId") Long guestId,
                           @RequestParam("roomId") Long roomId) {
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid guest Id:" + guestId));
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid room Id:" + roomId));
        stay.setGuest(guest);
        stay.setRoom(room);
        stayRepository.save(stay);
        return "redirect:/stays";
    }

    @PostMapping("/stays/edit/{id}")
    @Transactional
    public String updateStay(@PathVariable Long id,
                             @ModelAttribute("stay") Stay stayDetails,
                             @RequestParam("guestId") Long guestId,
                             @RequestParam("roomId") Long roomId) {
        Stay stay = stayRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid stay Id:" + id));
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid guest Id:" + guestId));
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid room Id:" + roomId));
        stay.setStartDate(stayDetails.getStartDate());
        stay.setEndDate(stayDetails.getEndDate());
        stay.setGuest(guest);
        stay.setRoom(room);
        stayRepository.save(stay);
        return "redirect:/stays";
    }

    @PostMapping("/stays/delete/{id}")
    @Transactional
    public String deleteStay(@PathVariable Long id) {
        Stay stay = stayRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid stay Id:" + id));
        stayRepository.delete(stay);
        return "redirect:/stays";
    }

    // Управление процедурами
    @GetMapping("/procedures")
    public String getProcedures(Model model) {
        List<Procedure> procedures = procedureRepository.findAll();
        model.addAttribute("procedures", procedures);
        return "procedures";
    }

    @PostMapping("/procedures/create")
    @Transactional
    public String saveProcedure(@ModelAttribute("procedure") Procedure procedure) {
        procedureRepository.save(procedure);
        return "redirect:/procedures";
    }

    @PostMapping("/procedures/edit/{id}")
    @Transactional
    public String updateProcedure(@PathVariable Long id, @ModelAttribute("procedure") Procedure procedureDetails) {
        Procedure procedure = procedureRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid procedure Id:" + id));
        procedure.setName(procedureDetails.getName());
        procedureRepository.save(procedure);
        return "redirect:/procedures";
    }

    @PostMapping("/procedures/delete/{id}")
    @Transactional
    public String deleteProcedure(@PathVariable Long id) {
        Procedure procedure = procedureRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid procedure Id:" + id));
        procedureRepository.delete(procedure);
        return "redirect:/procedures";
    }

    // Управление номерами
    @GetMapping("/rooms")
    public String getRooms(Model model) {
        List<Room> rooms = roomRepository.findAll();
        model.addAttribute("rooms", rooms);
        return "rooms";
    }

    @PostMapping("/rooms/create")
    @Transactional
    public String saveRoom(@ModelAttribute("room") Room room) {
        roomRepository.save(room);
        return "redirect:/rooms";
    }

    @PostMapping("/rooms/edit/{id}")
    @Transactional
    public String updateRoom(@PathVariable Long id, @ModelAttribute("room") Room roomDetails) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid room Id:" + id));
        room.setNumber(roomDetails.getNumber());
        roomRepository.save(room);
        return "redirect:/rooms";
    }

    @PostMapping("/rooms/delete/{id}")
    @Transactional
    public String deleteRoom(@PathVariable Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid room Id:" + id));
        roomRepository.delete(room);
        return "redirect:/rooms";
    }

    // Управление терапевтическими сеансами
    @GetMapping("/treatmentSessions")
    public String getTreatmentSessions(Model model) {
        List<TreatmentSession> treatmentSessions = treatmentSessionRepository.findAll();
        List<Guest> guests = guestRepository.findAll();
        List<Procedure> procedures = procedureRepository.findAll();
        model.addAttribute("treatmentSessions", treatmentSessions);
        model.addAttribute("guests", guests);
        model.addAttribute("procedures", procedures);
        return "treatmentSessions";
    }

    @PostMapping("/treatmentSessions/create")
    @Transactional
    public String saveTreatmentSession(@ModelAttribute("treatmentSession") TreatmentSession treatmentSession,
                                       @RequestParam("guestId") Long guestId,
                                       @RequestParam("procedureId") Long procedureId) {
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid guest Id:" + guestId));
        Procedure procedure = procedureRepository.findById(procedureId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid procedure Id:" + procedureId));
        treatmentSession.setGuest(guest);
        treatmentSession.setProcedure(procedure);
        treatmentSessionRepository.save(treatmentSession);
        return "redirect:/treatmentSessions";
    }

    @PostMapping("/treatmentSessions/edit/{id}")
    @Transactional
    public String updateTreatmentSession(@PathVariable Long id,
                                         @ModelAttribute("treatmentSession") TreatmentSession treatmentSessionDetails,
                                         @RequestParam("guestId") Long guestId,
                                         @RequestParam("procedureId") Long procedureId) {
        TreatmentSession treatmentSession = treatmentSessionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid treatment session Id:" + id));
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid guest Id:" + guestId));
        Procedure procedure = procedureRepository.findById(procedureId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid procedure Id:" + procedureId));
        treatmentSession.setGuest(guest);
        treatmentSession.setProcedure(procedure);
        treatmentSessionRepository.save(treatmentSession);
        return "redirect:/treatmentSessions";
    }

    @PostMapping("/treatmentSessions/delete/{id}")
    @Transactional
    public String deleteTreatmentSession(@PathVariable Long id) {
        TreatmentSession treatmentSession = treatmentSessionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid treatment session Id:" + id));
        treatmentSessionRepository.delete(treatmentSession);
        return "redirect:/treatmentSessions";
    }

    // Управление медицинскими обследованиями
    @GetMapping("/medicalExaminations")
    public String getMedicalExaminations(Model model) {
        List<MedicalExamination> medicalExaminations = medicalExaminationRepository.findAll();
        List<Guest> guests = guestRepository.findAll();
        model.addAttribute("medicalExaminations", medicalExaminations);
        model.addAttribute("guests", guests);
        return "medicalExaminations";
    }

    @PostMapping("/medicalExaminations/create")
    @Transactional
    public String saveMedicalExamination(@ModelAttribute("medicalExamination") MedicalExamination medicalExamination,
                                         @RequestParam("guest.id") Long guestId) {
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid guest Id:" + guestId));
        medicalExamination.setGuest(guest);
        medicalExaminationRepository.save(medicalExamination);
        return "redirect:/medicalExaminations";
    }


    @PostMapping("/medicalExaminations/edit/{id}")
    @Transactional
    public String updateMedicalExamination(@PathVariable Long id,
                                           @ModelAttribute("medicalExamination") MedicalExamination medicalExaminationDetails,
                                           @RequestParam("guest.id") Long guestId) {
        MedicalExamination medicalExamination = medicalExaminationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid medical examination Id:" + id));
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid guest Id:" + guestId));
        medicalExamination.setDate(medicalExaminationDetails.getDate());
        medicalExamination.setGuest(guest);
        medicalExaminationRepository.save(medicalExamination);
        return "redirect:/medicalExaminations";
    }


    @PostMapping("/medicalExaminations/delete/{id}")
    @Transactional
    public String deleteMedicalExamination(@PathVariable Long id) {
        MedicalExamination medicalExamination = medicalExaminationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid medical examination Id:" + id));
        medicalExaminationRepository.delete(medicalExamination);
        return "redirect:/medicalExaminations";
    }
}
