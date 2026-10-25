import { useEffect, useState, useCallback } from "react";
import { getCustomersApi } from "../../../api/customerApi";

import AddCustomerModal from "../../../components/CustomerPage/AddCustomerModal";
import SearchCustomerModal from "../../../components/CustomerPage/SearchCustomerModal";
import CustomerTable from "../../../components/CustomerPage/CustomerTable";

import styles from "../../../styles/CustomerPage.module.css";

export default function CustomerPage() {
  const [customers, setCustomers] = useState([]);
  const [showAdd, setShowAdd] = useState(false);
  const [showSearch, setShowSearch] = useState(false);

  const loadCustomers = useCallback(async () => {
    try {
      const res = await getCustomersApi();
      setCustomers(res.data);
    } catch (e) {
      console.error(e);
    }
  }, []);

  useEffect(() => {
    // eslint-disable-next-line react-hooks/set-state-in-effect
    loadCustomers();
  }, [loadCustomers]);

  return (
    <div className={styles.container}>
      <h1 className={styles.pageTitle}>Quản lý khách hàng</h1>

      {/* ACTION BAR */}
      <div className={styles.actionBar}>
        <div className={styles.actionGroup}>
          <button
            className={`${styles.btn} ${styles.primary}`}
            onClick={() => setShowAdd(true)}
          >
            + Thêm khách hàng
          </button>

          <button
            className={`${styles.btn} ${styles.secondary}`}
            onClick={() => setShowSearch(true)}
          >
            🔍 Tìm kiếm
          </button>
        </div>
      </div>

      {/* MODALS */}
      <AddCustomerModal
        open={showAdd}
        onClose={() => setShowAdd(false)}
        onSuccess={loadCustomers}
      />

      <SearchCustomerModal
        open={showSearch}
        onClose={() => setShowSearch(false)}
      />

      {/* TABLE */}
      <CustomerTable customers={customers} />
    </div>
  );
}