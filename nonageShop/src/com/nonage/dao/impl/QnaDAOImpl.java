package com.nonage.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.nonage.dao.QnaDAO;
import com.nonage.db.sqlConfig.IBatisDBConnector;
import com.nonage.dto.QnaVO;

public class QnaDAOImpl implements QnaDAO {
	private SqlMapClient client = IBatisDBConnector.getSqlMapClient();
	private static QnaDAOImpl instance = new QnaDAOImpl();

	private QnaDAOImpl() {
	}

	public static QnaDAOImpl getInstance() {
		return instance;
	}

	public ArrayList<QnaVO> listQna(String id) throws SQLException {
		ArrayList<QnaVO> qnaList = new ArrayList<QnaVO>();
		qnaList = (ArrayList<QnaVO>) client.queryForList("Qna.listQna", id);
		return qnaList;
	}

	public QnaVO getQna(int seq) throws SQLException {
		QnaVO qnaVO=(QnaVO)client.queryForObject("Qna.getQna",seq);
		return qnaVO;
	}

	public void insertqna(QnaVO qnaVO, String session_id) throws SQLException{		
		qnaVO.setId(session_id);
		client.insert("Qna.insertqna",qnaVO);
	}

	/* *
	 * 관리자 모드에서 필요한 메소드
	 */
	public ArrayList<QnaVO> listAllQna() throws SQLException {
		ArrayList<QnaVO> qnaList = new ArrayList<QnaVO>();
		// 게시판의 데이터를 가지고 오는 쿼리 rep:1:게시물 2: 답변
		qnaList=(ArrayList<QnaVO>)client.queryForList("Qna.listAllQna",null);
		return qnaList;
	}

	public void updateQna(QnaVO qnaVO) throws SQLException{
		client.update("Qna.updateQna",qnaVO);
	}
}
