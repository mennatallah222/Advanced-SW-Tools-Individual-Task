
import javax.ejb.Stateless;
import javax.persistence.*;

@Stateless
@Entity
public class Calculation {
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
	public Integer number1;
	public Integer number2;
	public String operation;


	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber1() {
        return number1;
    }

    public void setNumber1(Integer number1) {
        this.number1 = number1;
    }

    public Integer getNumber2() {
        return number2;
    }

    public void setNumber2(Integer number2) {
        this.number2 = number2;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

   
}
