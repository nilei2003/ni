package com.qq.biz;

import java.util.List;
import java.util.Map;

import com.qq.bean.Musicmv;
import com.qq.dao.MusicmvDao;
import com.qq.dao.impl.MusicmvDaoImpl;
import com.qq.util.StringUtils;

public class MusicmvBizimpl implements MusicmvDao{

	MusicmvDao dao = new MusicmvDaoImpl();
	// 上上传MV
	@Override
	public int musicmvAdd(Musicmv musicmv, String mname) {
		// TODO Auto-generated method stub
		if(StringUtils.pach(musicmv.getMvs(), musicmv.getMvname(), musicmv.getMvphoto(),
				            musicmv.getMvbj(), mname)) {
			
			return 4;
			
		}
		
		
		return dao.musicmvAdd(musicmv, mname);
	}
	@Override
	public List<Map<String, String>> showlist(int page, int rows) {
		// TODO Auto-generated method stub
		return dao.showlist(page, rows);
	}
	@Override
	public Map<String, String> show(String mvid) {
		// TODO Auto-generated method stub
		return dao.show(mvid);
	}

}
