package com.mati.demo.model.content;

import java.io.Serializable;

public interface Commentable extends Serializable {
	
	void comment(Comment comment);

}
