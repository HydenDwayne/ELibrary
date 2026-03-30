package controller;

import java.awt.*;
import java.time.*;
import java.util.List;

import javax.swing.*;

import model.DAOs.FunctionHall.*;
import view.facility_panels.*;

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

	public FunctionHallController(Amphitheater amphi, JPanel calendarGrid) {
		this.amphi = amphi;
		YearMonth currentMonth = amphi.getCurrentMonth();
		int daysInMonth = currentMonth.lengthOfMonth();
		
		List<DAOFuncHall> events = daoFuncHall.checkDayForEvent("LibAmphi");
		
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
	
	public FunctionHallController(DiscussionRoom1 disc1, JPanel calendarGrid) {
		this.disc1 = disc1;
		YearMonth currentMonth = disc1.getCurrentMonth();
		int daysInMonth = currentMonth.lengthOfMonth();
		
		List<DAOFuncHall> events = daoFuncHall.checkDayForEvent("DiscRoom");
		
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
	
	public FunctionHallController(DiscussionRoom2 disc2, JPanel calendarGrid) {
		this.disc2 = disc2;
		YearMonth currentMonth = disc2.getCurrentMonth();
		int daysInMonth = currentMonth.lengthOfMonth();
		
		List<DAOFuncHall> events = daoFuncHall.checkDayForEvent("DiscRoom");
		
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
	public FunctionHallController(MultipurposeHall1 multi1, JPanel calendarGrid) {
		this.multi1 = multi1;
		YearMonth currentMonth = multi1.getCurrentMonth();
		int daysInMonth = currentMonth.lengthOfMonth();
		
		List<DAOFuncHall> events = daoFuncHall.checkDayForEvent("MH1");
		
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
	
	public FunctionHallController(MultipurposeHall2 multi2, JPanel calendarGrid) {
		this.multi2 = multi2;
		YearMonth currentMonth = multi2.getCurrentMonth();
		int daysInMonth = currentMonth.lengthOfMonth();
		
		List<DAOFuncHall> events = daoFuncHall.checkDayForEvent("MH2");
		
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
	
	public FunctionHallController(SmartLearningRoom1 slr1, JPanel calendarGrid) {
		this.slr1 = slr1;
		YearMonth currentMonth = slr1.getCurrentMonth();
		int daysInMonth = currentMonth.lengthOfMonth();
		
		List<DAOFuncHall> events = daoFuncHall.checkDayForEvent("SLR");
		
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
	
	public FunctionHallController(SmartLearningRoom2 slr2, JPanel calendarGrid) {
		this.slr2 = slr2;
		YearMonth currentMonth = slr2.getCurrentMonth();
		int daysInMonth = currentMonth.lengthOfMonth();
		
		List<DAOFuncHall> events = daoFuncHall.checkDayForEvent("SLR");
		
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
	
	public FunctionHallController(TeleconferencingRoom tele, JPanel calendarGrid) {
		this.tele = tele;
		YearMonth currentMonth = tele.getCurrentMonth();
		int daysInMonth = currentMonth.lengthOfMonth();
		
		List<DAOFuncHall> events = daoFuncHall.checkDayForEvent("TeleconRoom");
		
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
}
