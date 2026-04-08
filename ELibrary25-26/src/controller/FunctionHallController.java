package controller;

import java.awt.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.awt.event.*;

import javax.swing.*;

import model.DAOs.FunctionHall.*;
import view.facility_panels.*;
import view.modal.function_hall_modal.ReserveFunctionHallModal;
import view.modal.function_hall_modal.ViewHallReservationModal;
import view.toolbar_tabs.OverviewTab;

public class FunctionHallController {
	
	private FuncHallDAOImp daoFuncHall = new FuncHallDAOImp();
	private Amphitheater amphi;
	private DiscussionRoom1 disc1;
	private DiscussionRoom2 disc2;
	private MultipurposeHall1 multi1;
	private MultipurposeHall2 multi2;
	private SmartLearningRoom1 slr1;
	private SmartLearningRoom2 slr2;
	private TeleconferencingRoom tele;
	private OverviewTab ovTab;
	
	public FunctionHallController(OverviewTab ovTab, JPanel calendarGrid) {
		this.ovTab = ovTab;
		YearMonth currentMonth = ovTab.getCurrentMonth();
		int daysInMonth = currentMonth.lengthOfMonth();
		
		List<DAOFuncHall> events = daoFuncHall.checkDayForEvent();
		
		for (int day = 1; day <= daysInMonth; day++) {
			

            JPanel cell = new JPanel(new BorderLayout());
            
            for(DAOFuncHall event: events) {
				String calendarDay = "01";
				if (day < 10) {
					calendarDay = "0" + day;
				} else {
					calendarDay = day+"";
				}
				String calendarMonth = "01";
				if (currentMonth.getMonthValue() < 10) {
					calendarMonth = "0" + currentMonth.getMonthValue();
				} else {
					calendarMonth = currentMonth.getMonthValue()+"";
				}
				String dateOfEvent = event.getDateOfEvent();
				String calendarDate = currentMonth.getYear()+"-"+calendarMonth+"-"+calendarDay;
				
				if (calendarDate.equals(dateOfEvent)) {
					cell.setBackground(Color.decode("#9f4542"));
				}

			}

            cell.setBorder(BorderFactory.createLineBorder(Color.GRAY));

            JLabel dateLabel = new JLabel(String.valueOf(day));
            dateLabel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
            dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);

            cell.add(dateLabel, BorderLayout.NORTH);

            calendarGrid.add(cell);
        }
	}

	public FunctionHallController(Amphitheater amphi, JPanel calendarGrid) {
        this.amphi = amphi;

        YearMonth currentMonth = amphi.getCurrentMonth();
        int daysInMonth = currentMonth.lengthOfMonth();

        List<DAOFuncHall> events = daoFuncHall.checkDayForEvent("LibAmphi");

        for (int day = 1; day <= daysInMonth; day++) {

            JPanel cell = new JPanel(new BorderLayout());
            cell.setBorder(BorderFactory.createLineBorder(Color.GRAY));

            
            String calendarDay = (day < 10) ? "0" + day : String.valueOf(day);
            String calendarMonth = (currentMonth.getMonthValue() < 10)
                    ? "0" + currentMonth.getMonthValue()
                    : String.valueOf(currentMonth.getMonthValue());

            String calendarDate = currentMonth.getYear() + "-" + calendarMonth + "-" + calendarDay;

            
            DAOFuncHall matchedEvent = null;

            for (DAOFuncHall event : events) {
                if (calendarDate.equals(event.getDateOfEvent())) {
                    cell.setBackground(Color.decode("#9f4542"));
                    matchedEvent = event;
                    break; 
                }
            }

         
            String finalCalendarDate = calendarDate;
            DAOFuncHall finalEvent = matchedEvent;

            
            cell.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        Window parent = SwingUtilities.getWindowAncestor(amphi);

                        if (finalEvent != null) {
                            
                            String startTimeRaw = finalEvent.getStartTime();
                            String endTimeRaw = finalEvent.getEndTime();

                            LocalTime startTime = LocalTime.parse(startTimeRaw,
                                    DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSSS"));
                            LocalTime endTime = LocalTime.parse(endTimeRaw,
                                    DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSSS"));

                            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("hh:mm a");

                            String[] eventDetails = {
                                    finalEvent.getHallReservationNumber(),
                                    finalEvent.getLibrarianID(),
                                    finalEvent.getPatronID(),
                                    finalEvent.getEventName(),
                                    finalEvent.getDateOfEvent(),
                                    startTime.format(outputFormatter),
                                    endTime.format(outputFormatter),
                            };

                            new ViewHallReservationModal(parent, eventDetails);

                        } else {
                            new ReserveFunctionHallModal(parent, "LibAmphi", finalCalendarDate);
                            amphi.generateCalendar();
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            
            JLabel dateLabel = new JLabel(String.valueOf(day));
            dateLabel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
            dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);

            cell.add(dateLabel, BorderLayout.NORTH);
            calendarGrid.add(cell);
        }
    }
	
	
	
	public FunctionHallController(String reservationNumber) {
		boolean isSuccessful = daoFuncHall.archiveReservation(reservationNumber);
		
		if (!isSuccessful) {
			JOptionPane.showMessageDialog(null, "Error. Event has not been cancelled");
		}
		
	}
	

	public FunctionHallController(String[] reservationDetails) {
		boolean isSuccessful = daoFuncHall.addNewReservation(reservationDetails);
		
		if (!isSuccessful) {
			JOptionPane.showMessageDialog(null, "Error. Date not Reserved");
		}
	}
	
	public FunctionHallController(DiscussionRoom1 disc1, JPanel calendarGrid) {
		this.disc1 = disc1;
		YearMonth currentMonth = disc1.getCurrentMonth();
		int daysInMonth = currentMonth.lengthOfMonth();
		
		List<DAOFuncHall> events = daoFuncHall.checkDayForEvent("DiscRoom1");
		
		for (int day = 1; day <= daysInMonth; day++) {

            JPanel cell = new JPanel(new BorderLayout());
            cell.setBorder(BorderFactory.createLineBorder(Color.GRAY));

            
            String calendarDay = (day < 10) ? "0" + day : String.valueOf(day);
            String calendarMonth = (currentMonth.getMonthValue() < 10)
                    ? "0" + currentMonth.getMonthValue()
                    : String.valueOf(currentMonth.getMonthValue());

            String calendarDate = currentMonth.getYear() + "-" + calendarMonth + "-" + calendarDay;

            
            DAOFuncHall matchedEvent = null;

            for (DAOFuncHall event : events) {
                if (calendarDate.equals(event.getDateOfEvent())) {
                    cell.setBackground(Color.decode("#9f4542"));
                    matchedEvent = event;
                    break; 
                }
            }

         
            String finalCalendarDate = calendarDate;
            DAOFuncHall finalEvent = matchedEvent;

            
            cell.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        Window parent = SwingUtilities.getWindowAncestor(disc1);

                        if (finalEvent != null) {
                            
                            String startTimeRaw = finalEvent.getStartTime();
                            String endTimeRaw = finalEvent.getEndTime();

                            LocalTime startTime = LocalTime.parse(startTimeRaw,
                                    DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSSS"));
                            LocalTime endTime = LocalTime.parse(endTimeRaw,
                                    DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSSS"));

                            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("hh:mm a");

                            String[] eventDetails = {
                                    finalEvent.getHallReservationNumber(),
                                    finalEvent.getLibrarianID(),
                                    finalEvent.getPatronID(),
                                    finalEvent.getEventName(),
                                    finalEvent.getDateOfEvent(),
                                    startTime.format(outputFormatter),
                                    endTime.format(outputFormatter),
                            };

                            new ViewHallReservationModal(parent, eventDetails);

                        } else {
                            new ReserveFunctionHallModal(parent, "DiscRoom1", finalCalendarDate);
                            disc1.generateCalendar();
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            
            JLabel dateLabel = new JLabel(String.valueOf(day));
            dateLabel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
            dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);

            cell.add(dateLabel, BorderLayout.NORTH);
            calendarGrid.add(cell);
        }
	}
	
	public FunctionHallController(DiscussionRoom2 disc2, JPanel calendarGrid) {
		this.disc2 = disc2;
		YearMonth currentMonth = disc2.getCurrentMonth();
		int daysInMonth = currentMonth.lengthOfMonth();
		
		List<DAOFuncHall> events = daoFuncHall.checkDayForEvent("DiscRoom2");
		
		for (int day = 1; day <= daysInMonth; day++) {

            JPanel cell = new JPanel(new BorderLayout());
            cell.setBorder(BorderFactory.createLineBorder(Color.GRAY));

            
            String calendarDay = (day < 10) ? "0" + day : String.valueOf(day);
            String calendarMonth = (currentMonth.getMonthValue() < 10)
                    ? "0" + currentMonth.getMonthValue()
                    : String.valueOf(currentMonth.getMonthValue());

            String calendarDate = currentMonth.getYear() + "-" + calendarMonth + "-" + calendarDay;

            
            DAOFuncHall matchedEvent = null;

            for (DAOFuncHall event : events) {
                if (calendarDate.equals(event.getDateOfEvent())) {
                    cell.setBackground(Color.decode("#9f4542"));
                    matchedEvent = event;
                    break; 
                }
            }

         
            String finalCalendarDate = calendarDate;
            DAOFuncHall finalEvent = matchedEvent;

            
            cell.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        Window parent = SwingUtilities.getWindowAncestor(disc2);

                        if (finalEvent != null) {
                            
                            String startTimeRaw = finalEvent.getStartTime();
                            String endTimeRaw = finalEvent.getEndTime();

                            LocalTime startTime = LocalTime.parse(startTimeRaw,
                                    DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSSS"));
                            LocalTime endTime = LocalTime.parse(endTimeRaw,
                                    DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSSS"));

                            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("hh:mm a");

                            String[] eventDetails = {
                                    finalEvent.getHallReservationNumber(),
                                    finalEvent.getLibrarianID(),
                                    finalEvent.getPatronID(),
                                    finalEvent.getEventName(),
                                    finalEvent.getDateOfEvent(),
                                    startTime.format(outputFormatter),
                                    endTime.format(outputFormatter),
                            };

                            new ViewHallReservationModal(parent, eventDetails);

                        } else {
                            new ReserveFunctionHallModal(parent, "DiscRoom2", finalCalendarDate);
                            disc2.generateCalendar();
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            
            JLabel dateLabel = new JLabel(String.valueOf(day));
            dateLabel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
            dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);

            cell.add(dateLabel, BorderLayout.NORTH);
            calendarGrid.add(cell);
        }
	}
	public FunctionHallController(MultipurposeHall1 multi1, JPanel calendarGrid) {
		this.multi1 = multi1;
		YearMonth currentMonth = multi1.getCurrentMonth();
		int daysInMonth = currentMonth.lengthOfMonth();
		
		List<DAOFuncHall> events = daoFuncHall.checkDayForEvent("MH1");
		
		for (int day = 1; day <= daysInMonth; day++) {

            JPanel cell = new JPanel(new BorderLayout());
            cell.setBorder(BorderFactory.createLineBorder(Color.GRAY));

            
            String calendarDay = (day < 10) ? "0" + day : String.valueOf(day);
            String calendarMonth = (currentMonth.getMonthValue() < 10)
                    ? "0" + currentMonth.getMonthValue()
                    : String.valueOf(currentMonth.getMonthValue());

            String calendarDate = currentMonth.getYear() + "-" + calendarMonth + "-" + calendarDay;

            
            DAOFuncHall matchedEvent = null;

            for (DAOFuncHall event : events) {
                if (calendarDate.equals(event.getDateOfEvent())) {
                    cell.setBackground(Color.decode("#9f4542"));
                    matchedEvent = event;
                    break; 
                }
            }

         
            String finalCalendarDate = calendarDate;
            DAOFuncHall finalEvent = matchedEvent;

            
            cell.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        Window parent = SwingUtilities.getWindowAncestor(multi1);

                        if (finalEvent != null) {
                            
                            String startTimeRaw = finalEvent.getStartTime();
                            String endTimeRaw = finalEvent.getEndTime();

                            LocalTime startTime = LocalTime.parse(startTimeRaw,
                                    DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSSS"));
                            LocalTime endTime = LocalTime.parse(endTimeRaw,
                                    DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSSS"));

                            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("hh:mm a");

                            String[] eventDetails = {
                                    finalEvent.getHallReservationNumber(),
                                    finalEvent.getLibrarianID(),
                                    finalEvent.getPatronID(),
                                    finalEvent.getEventName(),
                                    finalEvent.getDateOfEvent(),
                                    startTime.format(outputFormatter),
                                    endTime.format(outputFormatter),
                            };

                            new ViewHallReservationModal(parent, eventDetails);

                        } else {
                            new ReserveFunctionHallModal(parent, "MH1", finalCalendarDate);
                            multi1.generateCalendar();
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            
            JLabel dateLabel = new JLabel(String.valueOf(day));
            dateLabel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
            dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);

            cell.add(dateLabel, BorderLayout.NORTH);
            calendarGrid.add(cell);
        }
	}
	
	public FunctionHallController(MultipurposeHall2 multi2, JPanel calendarGrid) {
		this.multi2 = multi2;
		YearMonth currentMonth = multi2.getCurrentMonth();
		int daysInMonth = currentMonth.lengthOfMonth();
		
		List<DAOFuncHall> events = daoFuncHall.checkDayForEvent("MH2");
		
		for (int day = 1; day <= daysInMonth; day++) {

            JPanel cell = new JPanel(new BorderLayout());
            cell.setBorder(BorderFactory.createLineBorder(Color.GRAY));

            
            String calendarDay = (day < 10) ? "0" + day : String.valueOf(day);
            String calendarMonth = (currentMonth.getMonthValue() < 10)
                    ? "0" + currentMonth.getMonthValue()
                    : String.valueOf(currentMonth.getMonthValue());

            String calendarDate = currentMonth.getYear() + "-" + calendarMonth + "-" + calendarDay;

            
            DAOFuncHall matchedEvent = null;

            for (DAOFuncHall event : events) {
                if (calendarDate.equals(event.getDateOfEvent())) {
                    cell.setBackground(Color.decode("#9f4542"));
                    matchedEvent = event;
                    break; 
                }
            }

         
            String finalCalendarDate = calendarDate;
            DAOFuncHall finalEvent = matchedEvent;

            
            cell.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        Window parent = SwingUtilities.getWindowAncestor(multi2);

                        if (finalEvent != null) {
                            
                            String startTimeRaw = finalEvent.getStartTime();
                            String endTimeRaw = finalEvent.getEndTime();

                            LocalTime startTime = LocalTime.parse(startTimeRaw,
                                    DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSSS"));
                            LocalTime endTime = LocalTime.parse(endTimeRaw,
                                    DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSSS"));

                            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("hh:mm a");

                            String[] eventDetails = {
                                    finalEvent.getHallReservationNumber(),
                                    finalEvent.getLibrarianID(),
                                    finalEvent.getPatronID(),
                                    finalEvent.getEventName(),
                                    finalEvent.getDateOfEvent(),
                                    startTime.format(outputFormatter),
                                    endTime.format(outputFormatter),
                            };

                            new ViewHallReservationModal(parent, eventDetails);

                        } else {
                            new ReserveFunctionHallModal(parent, "MH2", finalCalendarDate);
                            multi2.generateCalendar();
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            
            JLabel dateLabel = new JLabel(String.valueOf(day));
            dateLabel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
            dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);

            cell.add(dateLabel, BorderLayout.NORTH);
            calendarGrid.add(cell);
        }
	}
	
	public FunctionHallController(SmartLearningRoom1 slr1, JPanel calendarGrid) {
		this.slr1 = slr1;
		YearMonth currentMonth = slr1.getCurrentMonth();
		int daysInMonth = currentMonth.lengthOfMonth();
		
		List<DAOFuncHall> events = daoFuncHall.checkDayForEvent("SLR1");
		
		for (int day = 1; day <= daysInMonth; day++) {

            JPanel cell = new JPanel(new BorderLayout());
            cell.setBorder(BorderFactory.createLineBorder(Color.GRAY));

            
            String calendarDay = (day < 10) ? "0" + day : String.valueOf(day);
            String calendarMonth = (currentMonth.getMonthValue() < 10)
                    ? "0" + currentMonth.getMonthValue()
                    : String.valueOf(currentMonth.getMonthValue());

            String calendarDate = currentMonth.getYear() + "-" + calendarMonth + "-" + calendarDay;

            
            DAOFuncHall matchedEvent = null;

            for (DAOFuncHall event : events) {
                if (calendarDate.equals(event.getDateOfEvent())) {
                    cell.setBackground(Color.decode("#9f4542"));
                    matchedEvent = event;
                    break; 
                }
            }

         
            String finalCalendarDate = calendarDate;
            DAOFuncHall finalEvent = matchedEvent;

            
            cell.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        Window parent = SwingUtilities.getWindowAncestor(slr1);

                        if (finalEvent != null) {
                            
                            String startTimeRaw = finalEvent.getStartTime();
                            String endTimeRaw = finalEvent.getEndTime();

                            LocalTime startTime = LocalTime.parse(startTimeRaw,
                                    DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSSS"));
                            LocalTime endTime = LocalTime.parse(endTimeRaw,
                                    DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSSS"));

                            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("hh:mm a");

                            String[] eventDetails = {
                                    finalEvent.getHallReservationNumber(),
                                    finalEvent.getLibrarianID(),
                                    finalEvent.getPatronID(),
                                    finalEvent.getEventName(),
                                    finalEvent.getDateOfEvent(),
                                    startTime.format(outputFormatter),
                                    endTime.format(outputFormatter),
                            };

                            new ViewHallReservationModal(parent, eventDetails);

                        } else {
                            new ReserveFunctionHallModal(parent, "SLR1", finalCalendarDate);
                            slr1.generateCalendar();
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            
            JLabel dateLabel = new JLabel(String.valueOf(day));
            dateLabel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
            dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);

            cell.add(dateLabel, BorderLayout.NORTH);
            calendarGrid.add(cell);
        }
	}
	
	public FunctionHallController(SmartLearningRoom2 slr2, JPanel calendarGrid) {
		this.slr2 = slr2;
		YearMonth currentMonth = slr2.getCurrentMonth();
		int daysInMonth = currentMonth.lengthOfMonth();
		
		List<DAOFuncHall> events = daoFuncHall.checkDayForEvent("SLR2");
		
		for (int day = 1; day <= daysInMonth; day++) {

            JPanel cell = new JPanel(new BorderLayout());
            cell.setBorder(BorderFactory.createLineBorder(Color.GRAY));

            
            String calendarDay = (day < 10) ? "0" + day : String.valueOf(day);
            String calendarMonth = (currentMonth.getMonthValue() < 10)
                    ? "0" + currentMonth.getMonthValue()
                    : String.valueOf(currentMonth.getMonthValue());

            String calendarDate = currentMonth.getYear() + "-" + calendarMonth + "-" + calendarDay;

            
            DAOFuncHall matchedEvent = null;

            for (DAOFuncHall event : events) {
                if (calendarDate.equals(event.getDateOfEvent())) {
                    cell.setBackground(Color.decode("#9f4542"));
                    matchedEvent = event;
                    break; 
                }
            }

         
            String finalCalendarDate = calendarDate;
            DAOFuncHall finalEvent = matchedEvent;

            
            cell.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        Window parent = SwingUtilities.getWindowAncestor(slr2);

                        if (finalEvent != null) {
                            
                            String startTimeRaw = finalEvent.getStartTime();
                            String endTimeRaw = finalEvent.getEndTime();

                            LocalTime startTime = LocalTime.parse(startTimeRaw,
                                    DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSSS"));
                            LocalTime endTime = LocalTime.parse(endTimeRaw,
                                    DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSSS"));

                            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("hh:mm a");

                            String[] eventDetails = {
                                    finalEvent.getHallReservationNumber(),
                                    finalEvent.getLibrarianID(),
                                    finalEvent.getPatronID(),
                                    finalEvent.getEventName(),
                                    finalEvent.getDateOfEvent(),
                                    startTime.format(outputFormatter),
                                    endTime.format(outputFormatter),
                            };

                            new ViewHallReservationModal(parent, eventDetails);

                        } else {
                            new ReserveFunctionHallModal(parent, "SLR2", finalCalendarDate);
                            slr2.generateCalendar();
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            
            JLabel dateLabel = new JLabel(String.valueOf(day));
            dateLabel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
            dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);

            cell.add(dateLabel, BorderLayout.NORTH);
            calendarGrid.add(cell);
        }
	}
	
	public FunctionHallController(TeleconferencingRoom tele, JPanel calendarGrid) {
		this.tele = tele;
		YearMonth currentMonth = tele.getCurrentMonth();
		int daysInMonth = currentMonth.lengthOfMonth();
		
		List<DAOFuncHall> events = daoFuncHall.checkDayForEvent("TeleconRoom");
		
		for (int day = 1; day <= daysInMonth; day++) {

            JPanel cell = new JPanel(new BorderLayout());
            cell.setBorder(BorderFactory.createLineBorder(Color.GRAY));

            
            String calendarDay = (day < 10) ? "0" + day : String.valueOf(day);
            String calendarMonth = (currentMonth.getMonthValue() < 10)
                    ? "0" + currentMonth.getMonthValue()
                    : String.valueOf(currentMonth.getMonthValue());

            String calendarDate = currentMonth.getYear() + "-" + calendarMonth + "-" + calendarDay;

            
            DAOFuncHall matchedEvent = null;

            for (DAOFuncHall event : events) {
                if (calendarDate.equals(event.getDateOfEvent())) {
                    cell.setBackground(Color.decode("#9f4542"));
                    matchedEvent = event;
                    break; 
                }
            }

         
            String finalCalendarDate = calendarDate;
            DAOFuncHall finalEvent = matchedEvent;

            
            cell.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        Window parent = SwingUtilities.getWindowAncestor(tele);

                        if (finalEvent != null) {
                            
                            String startTimeRaw = finalEvent.getStartTime();
                            String endTimeRaw = finalEvent.getEndTime();

                            LocalTime startTime = LocalTime.parse(startTimeRaw,
                                    DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSSS"));
                            LocalTime endTime = LocalTime.parse(endTimeRaw,
                                    DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSSS"));

                            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("hh:mm a");

                            String[] eventDetails = {
                                    finalEvent.getHallReservationNumber(),
                                    finalEvent.getLibrarianID(),
                                    finalEvent.getPatronID(),
                                    finalEvent.getEventName(),
                                    finalEvent.getDateOfEvent(),
                                    startTime.format(outputFormatter),
                                    endTime.format(outputFormatter),
                            };

                            new ViewHallReservationModal(parent, eventDetails);

                        } else {
                            new ReserveFunctionHallModal(parent, "TeleconRoom", finalCalendarDate);
                            tele.generateCalendar();
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            
            JLabel dateLabel = new JLabel(String.valueOf(day));
            dateLabel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
            dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);

            cell.add(dateLabel, BorderLayout.NORTH);
            calendarGrid.add(cell);
        }
	}
}
