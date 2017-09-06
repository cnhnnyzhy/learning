package com.zhy.java.basic;

import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.CpuInfo;

public class OSInfoPrint {

	public static void main(String[] args) {
		System.out.println(System.getProperty("java.library.path"));
		System.out.println(System.getProperty("os.name"));
		
		
		Sigar s = new Sigar();
		try {
			CpuInfo[] cpuInfoList = s.getCpuInfoList();
			for(CpuInfo cpuInfo : cpuInfoList){
				System.out.println(cpuInfo.getTotalCores());
			}
		} catch (SigarException e) {
			e.printStackTrace();
		}
	}

}
