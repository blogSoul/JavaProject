package Data;

public class Card {
	
	/*카드에 대한 내용으로
	 * 숫자와 모양 그리고 메소드로는 상대방과 카드비교해서 승패를 알려주는 isBig이라는 메소드가 있다*/
    private int number; // 1~ 10
    private int shape; // 10 Heart, 20 Space

    public Card(int number, int shape) {
        this.number = number;
        this.shape = shape;
    }

    public boolean isBig(Card card)
    {
        if(card.number > this.number)
        {
            return false;
        }
        else if(card.number < this.number)
        {
            return true;
        }
        else
        {
            if(card.shape > this.shape)
            {
                return true;
            }
            else if(card.shape > this.shape)
            {
                return false;
            }
        }
        return false;
    }

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getShape() {
		return shape;
	}

	public void setShape(int shape) {
		this.shape = shape;
	}
}
