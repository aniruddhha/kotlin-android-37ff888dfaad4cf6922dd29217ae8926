/*
 * This file is auto-generated.  DO NOT MODIFY.
 */
package com.ani.background;
// Declare any non-default types here with import statements

public interface IDataProvider extends android.os.IInterface
{
  /** Default implementation for IDataProvider. */
  public static class Default implements com.ani.background.IDataProvider
  {
    @Override public int provideData(java.lang.String source) throws android.os.RemoteException
    {
      return 0;
    }
    @Override
    public android.os.IBinder asBinder() {
      return null;
    }
  }
  /** Local-side IPC implementation stub class. */
  public static abstract class Stub extends android.os.Binder implements com.ani.background.IDataProvider
  {
    private static final java.lang.String DESCRIPTOR = "com.ani.background.IDataProvider";
    /** Construct the stub at attach it to the interface. */
    public Stub()
    {
      this.attachInterface(this, DESCRIPTOR);
    }
    /**
     * Cast an IBinder object into an com.ani.background.IDataProvider interface,
     * generating a proxy if needed.
     */
    public static com.ani.background.IDataProvider asInterface(android.os.IBinder obj)
    {
      if ((obj==null)) {
        return null;
      }
      android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
      if (((iin!=null)&&(iin instanceof com.ani.background.IDataProvider))) {
        return ((com.ani.background.IDataProvider)iin);
      }
      return new com.ani.background.IDataProvider.Stub.Proxy(obj);
    }
    @Override public android.os.IBinder asBinder()
    {
      return this;
    }
    @Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
    {
      java.lang.String descriptor = DESCRIPTOR;
      switch (code)
      {
        case INTERFACE_TRANSACTION:
        {
          reply.writeString(descriptor);
          return true;
        }
        case TRANSACTION_provideData:
        {
          data.enforceInterface(descriptor);
          java.lang.String _arg0;
          _arg0 = data.readString();
          int _result = this.provideData(_arg0);
          reply.writeNoException();
          reply.writeInt(_result);
          return true;
        }
        default:
        {
          return super.onTransact(code, data, reply, flags);
        }
      }
    }
    private static class Proxy implements com.ani.background.IDataProvider
    {
      private android.os.IBinder mRemote;
      Proxy(android.os.IBinder remote)
      {
        mRemote = remote;
      }
      @Override public android.os.IBinder asBinder()
      {
        return mRemote;
      }
      public java.lang.String getInterfaceDescriptor()
      {
        return DESCRIPTOR;
      }
      @Override public int provideData(java.lang.String source) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        int _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(source);
          boolean _status = mRemote.transact(Stub.TRANSACTION_provideData, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().provideData(source);
          }
          _reply.readException();
          _result = _reply.readInt();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      public static com.ani.background.IDataProvider sDefaultImpl;
    }
    static final int TRANSACTION_provideData = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    public static boolean setDefaultImpl(com.ani.background.IDataProvider impl) {
      // Only one user of this interface can use this function
      // at a time. This is a heuristic to detect if two different
      // users in the same process use this function.
      if (Stub.Proxy.sDefaultImpl != null) {
        throw new IllegalStateException("setDefaultImpl() called twice");
      }
      if (impl != null) {
        Stub.Proxy.sDefaultImpl = impl;
        return true;
      }
      return false;
    }
    public static com.ani.background.IDataProvider getDefaultImpl() {
      return Stub.Proxy.sDefaultImpl;
    }
  }
  public int provideData(java.lang.String source) throws android.os.RemoteException;
}
