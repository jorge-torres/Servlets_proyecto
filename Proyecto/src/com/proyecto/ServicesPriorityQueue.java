package com.proyecto;

import java.time.LocalTime;
import static java.time.temporal.ChronoUnit.MINUTES;

public class ServicesPriorityQueue {
    int size = 0, maxSize, intervalLength, capacityPerTurn;
    LocalTime startHour, endHour;
    int[] intervals;
    User [] heap;

    public ServicesPriorityQueue ( ServiceMetaData metaData ) {
        maxSize = metaData.getMaxSize();
        heap = new User[ maxSize ];
        startHour = metaData.getStartHour();
        endHour = metaData.getEndHour();
        intervalLength = metaData.getIntervalLength();
        intervals = new int[ (int) MINUTES.between(startHour, endHour) / intervalLength ];
        capacityPerTurn = metaData.getCapacityPerTurn();
    }

    public boolean isEmpty () {
        return size == 0;
    }

    public int size () {
        return size;
    }

    public LocalTime insert ( User user ) throws Exception {
        if ( size == maxSize && size > 0 ) {
            throw new Exception("La cola está llena.");
        }

        if ( user.getAttentionHour().compareTo(startHour) < 0
             || user.getAttentionHour().compareTo(endHour) > 0 ) {
            throw new Exception("La hora específicada no está dentro del rango de atención.");
        }

        if ( intervals[ (int) MINUTES.between( startHour, user.getAttentionHour() ) / intervalLength ] < capacityPerTurn ) {
            heap[size] = user;
            sinkUp(size);
            size++;
            final int INTERVAL_POS = (int) MINUTES.between( startHour, user.getAttentionHour() ) / intervalLength;
            ++intervals[ INTERVAL_POS ];
            return user.getAttentionHour();
        }

        return null;
    }

    public User extractMax () {
        if ( isEmpty() ) {
            return null;
        }
        User result = heap[ 0 ];
        heap[ 0 ] = heap[ size - 1 ];
        heap [ size - 1 ] = null;
        size--;
        if (size>1) {
        	sinkDown( 0 );
        }
        

        final int INTERVAL_POS = (int) MINUTES.between( startHour, result.getAttentionHour() ) / intervalLength;
        --intervals[ INTERVAL_POS ];
        return result;
    }

    public void remove ( String uid ) {
        int position = find( uid );
        LocalTime oldAttentionHour = heap[ position ].getAttentionHour();
        heap[ position ].setAttentionHour( LocalTime.MIN );
        sinkUp( position );
        extractMax();
        final int INTERVAL_POS = (int) MINUTES.between( startHour, oldAttentionHour ) / intervalLength;
        --intervals[ INTERVAL_POS ];
    }

    /*public void changeHour ( LocalTime newHour, String uid ) throws Exception {
        int position = find( uid );

        if ( position < 0 ) {
            throw new Exception("El usuario no se encuentra en la cola.");
        }

        LocalTime oldHour = heap[ position ].getAttentionHour();
        heap[ position ].setAttentionHour( newHour );

        if ( newHour.compareTo( oldHour ) < 0 ) {
            sinkUp(position);
        } else {
            sinkDown( position );
        }
    }*/

    private int find( String uid ) {
        for ( int i = 0; i < size; i++ ) {
            if ( heap[ i ].getUid().equals( uid ) ) {
                return i;
            }
        }

        return -1;
    }

    private void sinkUp ( int position ) {
        int parent = (position - 1) / 2;
        int current = position;

        LocalTime parentAttentionHour = heap[ parent ].getAttentionHour();
        LocalTime currentAttentionHour = heap[ current ].getAttentionHour();

        while ( current > 0 && parentAttentionHour.compareTo( currentAttentionHour ) >= 0) {
            if ( parentAttentionHour.compareTo( currentAttentionHour ) == 0
                 && heap[parent].getPriority() >= heap[current].getPriority() ) {
                break;
            }

            swap( current, parent );
            current = parent;
            currentAttentionHour = heap[ current ].getAttentionHour();

            parent = (parent - 1) / 2;
            parentAttentionHour = heap[ parent ].getAttentionHour();
        }
    }

    private void sinkDown ( int position ) {
        int maxIndex = swapIndexIfLesser( position );

        if ( position != maxIndex ) {
            swap( position, maxIndex );
            sinkDown( position );
        }
    }

    private int swapIndexIfLesser ( int position ) {
        int leftChild = 2 * position + 1;
        int rightChild = 2 * position + 2;

        LocalTime maxIndexAttentionHour = heap[ position ].getAttentionHour();
        LocalTime leftChildAttentionHour = LocalTime.MAX;
        LocalTime rightChildAttentionHour = LocalTime.MAX;

        if ( leftChild < size ) {
            leftChildAttentionHour = heap[ leftChild ].getAttentionHour();
        }
        if ( rightChild < size ) {
            rightChildAttentionHour = heap[ rightChild ].getAttentionHour();
        }

        boolean swapWithLeftChild = leftChildAttentionHour.compareTo( maxIndexAttentionHour ) < 0 ||
                ( leftChildAttentionHour.compareTo( maxIndexAttentionHour ) == 0
                        && heap[ leftChild ].getPriority() > heap[ position ].getPriority() );

        boolean swapWithRightChild = rightChildAttentionHour.compareTo( maxIndexAttentionHour ) < 0 ||
                ( rightChildAttentionHour.compareTo( maxIndexAttentionHour ) == 0
                        && heap[ rightChild ].getPriority() > heap[ position ].getPriority() );

        if ( swapWithLeftChild ) {
            position = leftChild;
        } else if ( swapWithRightChild ) {
            position = rightChild;
        }

        return position;
    }

    private void swap ( int a, int b ) {
        User temp = heap[ a ];
        heap[ a ] = heap[ b ];
        heap[ b ] = temp;
    }
}
