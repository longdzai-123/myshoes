package HoangLong.MyShoes.dto.statistic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillStatistic {
	private long quantity;
	
	private String time;
}
