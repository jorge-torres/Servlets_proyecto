package com.proyecto;

public class Constcola {
	
	public static ServicesPriorityQueue cola ;
	public static ServiceMetaData metaData;
	public static BufferQueue bufferQueue;
	
	
	
	public static ServicesPriorityQueue  getPriorityQueueInstance() {
	    if ( cola == null ) {
	        cola = new ServicesPriorityQueue(metaData);
	    }

	    return cola;
	}

	public static  BufferQueue getBufferQueueInstance() {
	    if ( bufferQueue == null ) {
	        bufferQueue = new BufferQueue(metaData, getPriorityQueueInstance().intervals.length);
	    }

	    return bufferQueue;
	}
	
	public static void setMetaData (ServiceMetaData meta) {
	    metaData = meta;
	    
	    deleteQueue();
	}

	public static void deleteQueue() {
	    cola = null;
	    bufferQueue = null;
	}

}
