package gov.hhs.fha.nhinc.kmr;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import org.drools.runtime.rule.AccumulateFunction;

/**
 * An implementation of an accumulator capable of calculating average values
 * over a number of sorted values.
 *
 * @author etirelli
 *
 */
public class AverageLastAccumulateFunction implements AccumulateFunction {



    public static class AverageData implements Serializable {
        private static final long serialVersionUID = 8952364753943724669L;

        public TreeSet<DataEntry> set = new TreeSet<DataEntry>( new Comparator<DataEntry>() {
            public int compare(DataEntry o1, DataEntry o2) {
                return (o1.key - o2.key) > 0 ? 1 :
                    (o1.key - o2.key) == 0 ? 0 :
                    -1;
            }
        });
        public int length;

        public AverageData() {}
    }

    public static class DataEntry implements Serializable {
        private static final long serialVersionUID = 2065979543651872453L;

        public long key;
        public double value;

        public DataEntry( long key, double value ) {
            this.key = key;
            this.value = value;
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (int) (key ^ (key >>> 32));
            long temp;
            temp = Double.doubleToLongBits( value );
            result = prime * result + (int) (temp ^ (temp >>> 32));
            return result;
        }
        @Override
        public boolean equals(Object obj) {
            if ( this == obj ) return true;
            if ( obj == null ) return false;
            if ( getClass() != obj.getClass() ) return false;
            DataEntry other = (DataEntry) obj;
            if ( key != other.key ) return false;
            if ( Double.doubleToLongBits( value ) != Double.doubleToLongBits( other.value ) ) return false;
            return true;
        }
    }

    /* (non-Javadoc)
     * @see org.drools.base.accumulators.AccumulateFunction#createContext()
     */
    public Serializable createContext() {
        return new AverageData();
    }

    /* (non-Javadoc)
     * @see org.drools.base.accumulators.AccumulateFunction#init(java.lang.Object)
     */
    public void init(Serializable context) throws Exception {
        AverageData data = (AverageData) context;
        data.set.clear();
    }

    /* (non-Javadoc)
     * @see org.drools.base.accumulators.AccumulateFunction#accumulate(java.lang.Object, java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    public void accumulate(Serializable context,
                           Object value) {
        AverageData data = (AverageData) context;
        List<Object> params = (List<Object>) value;
        data.length = ((Number)params.get( 0 )).intValue();
        data.set.add( new DataEntry( ((Date)params.get(1)).getTime(), ((Number)params.get( 2 )).doubleValue()) );
    }

    /* (non-Javadoc)
     * @see org.drools.base.accumulators.AccumulateFunction#reverse(java.lang.Object, java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    public void reverse(Serializable context,
                        Object value) throws Exception {
        AverageData data = (AverageData) context;
        List<Object> params = (List<Object>) value;
        data.set.remove( new DataEntry( ((Date)params.get( 1 )).getTime(), ((Number)params.get( 2 )).doubleValue()) );
    }

    /* (non-Javadoc)
     * @see org.drools.base.accumulators.AccumulateFunction#getResult(java.lang.Object)
     */
    public Object getResult(Serializable context) throws Exception {
        AverageData data = (AverageData) context;
        Iterator<DataEntry> it = data.set.descendingIterator();
        double total = 0;
        for( int i = 0; i < data.length && it.hasNext(); i++ ) {
            total += it.next().value;
        }
        return new Double( data.length == 0 ? 0 : total / data.length );
    }

    /* (non-Javadoc)
     * @see org.drools.base.accumulators.AccumulateFunction#supportsReverse()
     */
    public boolean supportsReverse() {
        return true;
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException,
                                            ClassNotFoundException {
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
    }

}
