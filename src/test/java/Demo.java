import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor @ToString @EqualsAndHashCode
	class Demo{
		private int id;
		@NonNull
		private String name;
		@NonNull
		//private BigDecimal marks= new BigDecimal("0");
		private BigDecimal marks;
		@NonNull
		//private Set<String> set= new HashSet<>();
		private Set<String> set;
		@NonNull
		private Date date;
		@NonNull
		private Temp temp;
	}

	class Temp{}